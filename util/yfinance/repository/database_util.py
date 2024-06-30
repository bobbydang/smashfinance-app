from sqlalchemy_utils import database_exists, create_database, drop_database

from .database import engine_url_dev, engine_url_test, dev_engine, test_engine


class DatabaseUtil:

    @staticmethod
    def create_drop_dev_db():
        if database_exists(engine_url_dev):
            drop_database(engine_url_dev)
        create_database(engine_url_dev)

    @staticmethod
    def create_drop_test_db():
        if database_exists(engine_url_test):
            drop_database(engine_url_test)
        create_database(engine_url_test)

    @staticmethod
    def get_dev_engine():
        return dev_engine

    @staticmethod
    def get_test_engine():
        return test_engine
