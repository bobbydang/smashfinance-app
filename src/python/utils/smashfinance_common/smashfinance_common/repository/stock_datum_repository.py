import pandas as pd
from pyrate_limiter import Duration, Limiter, MemoryQueueBucket, RequestRate
import requests_cache
import yfinance as yf

from sqlalchemy.orm import sessionmaker

from .cached_limiter_session import CachedLimiterSession
from ..database import DatabaseUtil
from ..models import Stock
from ..models import StockDatum


class StockDatumRespository:

    def __init__(self):

        self.__engine = DatabaseUtil.DB_ENGINE
        self.__base = DatabaseUtil.BASE

        # Create tables in the correct order
        self.__base.metadata.create_all(self.__engine, checkfirst=True)

        self.__rate_limiter_session = CachedLimiterSession(
            limiter=Limiter(
                RequestRate(10, Duration.SECOND * 5)
            ),  # max 10 requests per 5 seconds
            bucket_class=MemoryQueueBucket,
            backend=requests_cache.SQLiteCache("yfinance.cache"),
        )

        self.__SessionFactory = sessionmaker(bind=self.__engine)
        self.__session = None

    def get_session(self):
        if self.__session is None:
            self.__session = self.__SessionFactory()
        return self.__session

    def close_session(self):
        if self.__session:
            self.__session.close()
            self.__session = None

    def download_and_save_data(self, company, start_date, end_date):
        # Download stock data
        ticker = company.get("tickerSymbol")
        data = yf.download(ticker, start=start_date,
                           end=end_date, session=self.__rate_limiter_session)
        data.reset_index(inplace=True)

        data["Date"] = pd.to_datetime(data["Date"])

        # Get a session
        session = self.get_session()

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

        # Commit the transaction
        session.commit()

        # Close the session
        session.close()
