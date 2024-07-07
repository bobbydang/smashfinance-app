
from repository.database_util import DatabaseUtil
from repository.stock_datum_dao import StockDatumDAO

from repository.database import engine_url_dev, engine_url_test
from seed_util import SeedUtil

DatabaseUtil.create_drop_dev_db()
DatabaseUtil.create_drop_test_db()

dev_stock = StockDatumDAO(engine_url_dev)
test_stock = StockDatumDAO(engine_url_test)

companies = SeedUtil.get_stock_data()

for company in companies.get("stocks"):

    dev_stock.download_and_save_data(company, "2023-01-01", "2023-12-31")
    test_stock.download_and_save_data(company, "2023-01-01", "2023-12-31")
    print(
        f"Stock data for {company.get('tickerSymbol')} has been inserted into the database.")
