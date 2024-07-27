# captialize name below
import os
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base

dev_db_name = "smashfinance_dev"
test_db_name = "smashfinance_test"
target_db_name = "postgres"
db_user = "postgres"
db_pass = os.getenv("DB_PASSWORD")
db_host = "localhost"  # or your database host
db_port = "5432"
cache_expire_after = 3600  # seconds

engine_url_dev = (
    f"postgresql+psycopg2://{db_user}:{db_pass}@{db_host}:{db_port}/{dev_db_name}"
)

engine_url_test = (
    f"postgresql+psycopg2://{db_user}:{db_pass}@{db_host}:{db_port}/{test_db_name}"
)

dev_engine = create_engine(engine_url_dev)

test_engine = create_engine(engine_url_test)

Base = declarative_base()

__all__ = ['Base', 'create_engine', 'dev_engine',
           'test_engine', 'engine_url_dev', 'engine_url_test']
