from sqlalchemy.orm import relationship
from sqlalchemy import (
    Column,
    String,
)

from ..database import DatabaseUtil


class Stock(DatabaseUtil.BASE):
    __tablename__ = 'stocks'
    symbol = Column(String(10), primary_key=True)
    name = Column(String(100), unique=True, nullable=False)
    sector = Column(String(255))
    industry = Column(String(255))
    country = Column(String(255))

    def __repr__(self):
        return f'<Stock(symbol={self.symbol}, name={self.name})>'
