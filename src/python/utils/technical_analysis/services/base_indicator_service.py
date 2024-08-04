import numpy as np
from smashfinance_common.repository.stock_datum_repository import StockDatumRespository

from technical_analysis.config.configuration_service import ConfigurationService


class BaseIndicatorService:
    def __init__(self, offset=100):
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

    def _get_closing_prices(self, stock_symbol):
        stock_data = self._get_data(stock_symbol)
        close_prices = [stock_datum.adj_close for stock_datum in stock_data]
        close_prices = np.array(close_prices, dtype=float)

        return close_prices
