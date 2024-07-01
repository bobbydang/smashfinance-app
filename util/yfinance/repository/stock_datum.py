

from sqlalchemy import BigInteger, Column, Date, DateTime, ForeignKey, Numeric, Sequence, String
from sqlalchemy.orm import relationship, declarative_base

from .database import Base


class StockDatum(Base):
    __tablename__ = "stock_data"
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    date = Column(DateTime(timezone=False))
    open = Column(Numeric(10, 2))
    high = Column(Numeric(10, 2))
    low = Column(Numeric(10, 2))
    close = Column(Numeric(10, 2))
    adj_close = Column(Numeric(10, 2))
    volume = Column(Numeric(20, 0))
    stock_company_symbol = Column(
        String(10), ForeignKey("stocks.symbol"))
    stocks = relationship("StockCompany", back_populates="stock_data")
