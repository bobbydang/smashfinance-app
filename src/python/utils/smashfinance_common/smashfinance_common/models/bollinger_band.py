from sqlalchemy import Column, DateTime, ForeignKey, Integer, Numeric, String, BigInteger
from sqlalchemy.orm import relationship
from ..database import DatabaseUtil


class BollingerBand(DatabaseUtil.BASE):
    __tablename__ = 'bollinger_bands'
    id = Column(BigInteger, primary_key=True, autoincrement=True)
    date = Column(DateTime(timezone=False), nullable=False)
    upper_band = Column(Numeric(10, 2), nullable=False)
    middle_band = Column(Numeric(10, 2), nullable=False)
    lower_band = Column(Numeric(10, 2), nullable=False)
    matype = Column(Integer, nullable=False)
    period = Column(Integer, nullable=False)
    nb_std_dev_up = Column(Integer, nullable=False)
    nb_std_dev_down = Column(Integer, nullable=False)
    stock_symbol = Column(String(10), ForeignKey(
        'stocks.symbol'), nullable=False)
    stock = relationship('Stock', backref='bollinger_bands')

    def __repr__(self):
        return f'<BollingerBand(stock_symbol={self.stock_symbol}, date={self.date}, upper_band={self.upper_band}, middle_band={self.middle_band}, lower_band={self.lower_band}, ma_type={self.matype}, time_period={self.period}, std_dev_up={self.nb_std_dev_up}, std_dev_up={self.nb_std_dev_down})>'
