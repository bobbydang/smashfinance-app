
import numpy as np
from smashfinance_common.common.decorators import singleton
from smashfinance_common.repository.stock_datum_repository import StockDatumRespository

from ..config.configuration_service import ConfigurationService
import talib


@singleton
class EmaIndicatorService:
    def __init__(self):
        self._OFFSET = ConfigurationService().get_pagination_config().get("offset")

    def _get_data(self, stock_symbol):
        stock_data = []
        stock_datum_repository = StockDatumRespository()
        count = stock_datum_repository.stock_data_by_stock_symbol_count(
            stock_symbol)

        num_intervals = count // self._OFFSET

        for i in range(num_intervals):
            stock_data.extend(stock_datum_repository.get_stock_data_by_stock_symbol_and_offset(
                stock_symbol, i * self._OFFSET, self._OFFSET))

        remainder = count % self._OFFSET

        if remainder > 0:
            stock_data.extend(stock_datum_repository.get_stock_data_by_stock_symbol_and_offset(
                stock_symbol, num_intervals * self._OFFSET, remainder))

        return stock_data

    def calculate_ema(self, stock_symbol, period):
        stock_data = []
        stock_data = self._get_data(stock_symbol)
        close_prices = [stock_datum.adj_close for stock_datum in stock_data]
        close_prices = np.array(close_prices, dtype=float)

        ema = talib.EMA(close_prices, timeperiod=period)
        return ema
