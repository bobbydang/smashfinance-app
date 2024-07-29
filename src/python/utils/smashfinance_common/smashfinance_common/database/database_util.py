from sqlalchemy import create_engine
from sqlalchemy_utils import database_exists, create_database, drop_database
from sqlalchemy.ext.declarative import declarative_base

from .database_config import engine_url


class DatabaseUtil:

    BASE = declarative_base()

    DB_ENGINE = create_engine(engine_url)

    @ staticmethod
    def create_drop_db():
        if database_exists(engine_url):
            drop_database(engine_url)
        create_database(engine_url)
