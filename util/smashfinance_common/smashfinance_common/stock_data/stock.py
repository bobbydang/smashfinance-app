from sqlalchemy.orm import relationship
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import (
    Column,
    String,
)

from ..database import Base


class Stock(Base):
    __tablename__ = 'stocks'
    symbol = Column(String(10), primary_key=True)
    name = Column(String(100), unique=True, nullable=False)
    sector = Column(String(255))
    industry = Column(String(255))
    country = Column(String(255))
    stock_data = relationship('StockDatum', back_populates='stocks')
