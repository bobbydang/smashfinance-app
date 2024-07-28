from sqlalchemy import BigInteger, Column, DateTime, ForeignKey, Numeric, String
from sqlalchemy.orm import relationship

from ..database import DatabaseUtil


class StockDatum(DatabaseUtil.BASE):
    __tablename__ = "stock_data"
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    date = Column(DateTime(timezone=False))
    open = Column(Numeric(10, 2))
    high = Column(Numeric(10, 2))
    low = Column(Numeric(10, 2))
    close = Column(Numeric(10, 2))
    adj_close = Column(Numeric(10, 2))
    volume = Column(Numeric(20, 0))
    stock_symbol = Column(
        String(10), ForeignKey("stocks.symbol"))
    stocks = relationship("Stock", back_populates="stock_data")
