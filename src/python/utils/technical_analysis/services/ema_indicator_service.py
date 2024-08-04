

import numpy as np
from smashfinance_common.common.decorators import singleton
from smashfinance_common.common.smash_finance_logger import SmashFinanceLogger
from smashfinance_common.models.exponential_moving_average import ExponentialMovingAverage
from smashfinance_common.repository.exponential_moving_average_repository import ExponentialMovingAverageRepository
from smashfinance_common.repository.stock_datum_repository import StockDatumRespository

from technical_analysis.services.base_indicator_service import BaseIndicatorService

from ..config.configuration_service import ConfigurationService
import talib


@singleton
class EmaIndicatorService(BaseIndicatorService):
    def __init__(self):
        super().__init__()
        self._logger = SmashFinanceLogger().get_logger(__name__)

    def calculate_and_save_ema(self, stock_symbol, period):

        stock_data = self._get_data(stock_symbol)
        close_prices = self._get_closing_prices(stock_symbol)

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
