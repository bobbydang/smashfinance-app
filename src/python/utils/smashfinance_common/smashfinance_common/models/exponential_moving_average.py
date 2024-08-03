from sqlalchemy import Column, DateTime, ForeignKey, Integer, Numeric, String, BigInteger
from sqlalchemy.orm import relationship
from ..database import DatabaseUtil


class ExponentialMovingAverage(DatabaseUtil.BASE):
    __tablename__ = 'exponential_moving_averages'
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    date = Column(DateTime(timezone=False), nullable=False)
    value = Column(Numeric(10, 2), nullable=False)
    period = Column(Integer, nullable=False)
    stock_symbol = Column(String(10), ForeignKey(
        'stocks.symbol'), nullable=False)
    stock = relationship(
        'Stock', backref='exponential_moving_averages')

    def __repr__(self):
        return f'<ExponentialMovingAverage(stock_symbol={self.stock_symbol}, date={self.date}, value={self.value}, period={self.period})>'
