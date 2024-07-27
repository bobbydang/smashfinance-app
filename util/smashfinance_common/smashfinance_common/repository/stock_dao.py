from sqlalchemy import (
    create_engine,
)

from sqlalchemy.orm import sessionmaker

from ..database import Base

from ..stock_data import Stock, StockDatum


class StockDAO:
    def __init__(self, db_url):
        self.__engine = create_engine(db_url)

        # Create tables in the correct order
        Base.metadata.create_all(self.__engine, checkfirst=True)

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

    def add_stock_company(self, stock_company_data):
        session = self.get_session()
        try:
            stock_company = Stock(**stock_company_data)
            session.add(stock_company)
            session.commit()
        except:
            session.rollback()
            raise
        finally:
            session.close()
