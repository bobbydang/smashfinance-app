
from smashfinance_common.database import DatabaseUtil
from smashfinance_common.repository import StockDatumRespository

from smashfinance_common.database import engine_url
from seed_util import SeedUtil

DatabaseUtil.create_drop_db()

stock = StockDatumRespository()

companies = SeedUtil.get_stock_data()

for company in companies.get("stocks"):

    stock.download_and_save_data(company, "2023-01-01", "2023-12-31")
    print(
        f"Stock data for {company.get('tickerSymbol')} has been inserted into the database.")
