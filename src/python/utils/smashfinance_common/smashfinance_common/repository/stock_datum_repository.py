import pandas as pd
from pyrate_limiter import Duration, Limiter, MemoryQueueBucket, RequestRate
import requests_cache
from smashfinance_common.repository.base_repository import BaseRepository
import yfinance as yf


from .cached_limiter_session import CachedLimiterSession
from ..models import Stock
from ..models import StockDatum


class StockDatumRespository(BaseRepository):

    def __init__(self):
        super().__init__()
        self.__rate_limiter_session = CachedLimiterSession(
            limiter=Limiter(
                RequestRate(10, Duration.SECOND * 5)
            ),  # max 10 requests per 5 seconds
            bucket_class=MemoryQueueBucket,
            backend=requests_cache.SQLiteCache("yfinance.cache"),
        )

    def download_and_save_data(self, company, start_date, end_date):
        # Download stock data
        ticker = company.get("tickerSymbol")
        data = yf.download(ticker, start=start_date,
                           end=end_date, session=self.__rate_limiter_session)
        data.reset_index(inplace=True)

        data["Date"] = pd.to_datetime(data["Date"])

        with self.session_scope() as session:

            # Check if the StockCompany exists
            stock_company = session.query(
                Stock).filter_by(symbol=ticker).first()

            # If the StockCompany does not exist, create it
            if not stock_company:
                stock_company = Stock(
                    symbol=ticker,
                    # Placeholder name, should be replaced with real data
                    name=company.get("companyName", "Unknown"),
                    # Placeholder sector, should be replaced with real data
                    sector=company.get("sector", "Unknown"),
                    # Placeholder industry, should be replaced with real data
                    industry=company.get("industry", "Unknown"),
                    # Placeholder country, should be replaced with real data
                    country=company.get("country", "Unknown")
                )
                session.add(stock_company)
                session.commit()  # Commit to get the symbol in the database

            # Create and add new StockDatum records
            for index, row in data.iterrows():
                stock_datum = StockDatum(
                    date=row['Date'],
                    open=row['Open'],
                    high=row['High'],
                    low=row['Low'],
                    close=row['Close'],
                    adj_close=row['Adj Close'],
                    volume=row['Volume'],
                    stock_symbol=ticker
                )
                session.add(stock_datum)
