from sqlalchemy import Column, DateTime, ForeignKey, Integer, Numeric, String, BigInteger
from sqlalchemy.orm import relationship
from ..database import DatabaseUtil


class BollingerBand(DatabaseUtil.BASE):

    id = Column(BigInteger, primary_key=True, autoincrement=True)
    date = Column(DateTime(timezone=False), nullable=False)
    upper_band = Column(Numeric(10, 2), nullable=False)
    middle_band = Column(Numeric(10, 2), nullable=False)
    lower_band = Column(Numeric(10, 2), nullable=False)
    ma_type = Column(Integer, nullable=False)
    time_period = Column(Integer, nullable=False)
    std_dev = Column(Integer, nullable=False)
    stock_symbol = Column(String(10), ForeignKey(
        'stocks.symbol'), nullable=False)
    stock = relationship('Stock', backref='bollinger_bands')

    def __repr__(self):
        return f'<BollingerBand(stock_symbol={self.stock_symbol}, date={self.date}, upper_band={self.upper_band}, middle_band={self.middle_band}, lower_band={self.lower_band}, ma_type={self.ma_type}, time_period={self.time_period}, std_dev={self.std_dev})>'
