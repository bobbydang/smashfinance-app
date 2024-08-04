

import numpy as np
from smashfinance_common.common.decorators import singleton
from smashfinance_common.common.smash_finance_logger import SmashFinanceLogger
from smashfinance_common.models.exponential_moving_average import ExponentialMovingAverage
from smashfinance_common.repository.exponential_moving_average_repository import ExponentialMovingAverageRepository
from smashfinance_common.repository.stock_datum_repository import StockDatumRespository

from ..config.configuration_service import ConfigurationService
import talib


@singleton
class EmaIndicatorService:
    def __init__(self):
        self._OFFSET = ConfigurationService().get_pagination_config().get("offset")
        self._logger = SmashFinanceLogger().get_logger(__name__)

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

    def calculate_and_save_ema(self, stock_symbol, period):

        stock_data = self._get_data(stock_symbol)

        close_prices = [stock_datum.adj_close for stock_datum in stock_data]
        close_prices = np.array(close_prices, dtype=float)

        ema_values = talib.EMA(close_prices, timeperiod=period)

        if len(ema_values) != len(close_prices):
            raise ValueError(
                "The length of the EMA values does not match the length of the close prices.")

        count = len(ema_values) - 1
        repository = ExponentialMovingAverageRepository()
        for index, (stock_datum, ema_value) in enumerate(zip(stock_data, ema_values)):
            ema_instance = ExponentialMovingAverage(
                date=stock_datum.date,
                value=ema_value,
                period=period,
                stock_symbol=stock_symbol)
            if not np.isnan(ema_value):
                # self._logger.info(ema_instance)
                repository.create(ema_instance, [
                    "date", "period", "stock_symbol"])
