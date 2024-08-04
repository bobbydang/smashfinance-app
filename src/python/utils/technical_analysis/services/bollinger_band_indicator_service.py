import numpy as np
from smashfinance_common.common.smash_finance_logger import SmashFinanceLogger
from smashfinance_common.models.bollinger_band import BollingerBand
from smashfinance_common.repository.bollinger_band_repository import BollingerBandRepository
import talib
from technical_analysis.config.configuration_service import ConfigurationService
from technical_analysis.services.base_indicator_service import BaseIndicatorService


class BollingerBandIndicatorService(BaseIndicatorService):

    def __init__(self):
        self._OFFSET = ConfigurationService().get_pagination_config().get("offset")
        self._logger = SmashFinanceLogger().get_logger(__name__)

    def calculate_and_save_bollinger_bands(self, stock_symbol, period, nb_std_dev_up, nb_std_dev_down, matype):

        stock_data = self._get_data(stock_symbol)
        close_prices = self._get_closing_prices(stock_symbol)

        bb_values = talib.BBANDS(
            close_prices, timeperiod=period, nbdevup=nb_std_dev_up, nbdevdn=nb_std_dev_down, matype=matype)

        if len(bb_values[0]) != len(close_prices):
            raise ValueError(
                "The length of the Bollinger Band values does not match the length of the close prices.")

        count = len(bb_values[0]) - 1
        repository = BollingerBandRepository()
        for index, (stock_datum, upper_band, middle_band, lower_band) in enumerate(zip(stock_data, *bb_values)):
            # Create a BollingerBand instance with the corresponding values
            bb_instance = BollingerBand(
                date=stock_datum.date,
                upper_band=upper_band,
                middle_band=middle_band,
                lower_band=lower_band,
                matype=matype,
                period=period,
                nb_std_dev_up=nb_std_dev_up,
                nb_std_dev_down=nb_std_dev_down,
                stock_symbol=stock_symbol
            )

            # Check if any of the band values are NaN
            if not np.isnan(upper_band) and not np.isnan(middle_band) and not np.isnan(lower_band):
                self._logger.info(bb_instance)
                repository.create(bb_instance, [
                    "date", "period", "stock_symbol", "upper_band", "middle_band", "lower_band", "matype", "nb_std_dev_up", "nb_std_dev_down"
                ])