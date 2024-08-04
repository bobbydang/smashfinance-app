

from pathlib import Path

import numpy as np
import yaml
from technical_analysis.services.bollinger_band_indicator_service import BollingerBandIndicatorService
from technical_analysis.services.ema_indicator_service import EmaIndicatorService


def main():
    # populate_ema()
    populate_bollinger_bands()


def get_stocks():
    base_dir = Path(__file__).resolve().parent

    try:
        with open(f"{base_dir}/assets/stocks.yml", "r") as f:
            stocks = yaml.safe_load(f)
    except FileNotFoundError:
        raise RuntimeError("Stocks file not found.")
    except yaml.YAMLError as e:
        raise RuntimeError(f"Error parsing stocks file: {e}")

    return stocks


def populate_ema():
    ema_service = EmaIndicatorService()
    stocks = get_stocks()
    stock_symbols = stocks.get("stocks")

    for stock_symbol in stock_symbols:
        for period in range(2, 31):
            ema_service.calculate_and_save_ema(
                stock_symbol['tickerSymbol'], period)

    print("Successfully calculated and saved EMA values.")


def populate_bollinger_bands():
    bb_service = BollingerBandIndicatorService()
    stocks = get_stocks()
    stock_symbols = stocks.get("stocks")

    for stock_symbol in stock_symbols:
        for period in range(10, 31):
            for nb_std_dev_up in np.arange(1.5, 3.0, 0.1):
                for nb_std_dev_down in np.arange(1.5, 3.0, 0.1):

                    bb_service.calculate_and_save_bollinger_bands(stock_symbol['tickerSymbol'],
                                                                  period, nb_std_dev_up, nb_std_dev_down, 0)


if __name__ == "__main__":
    main()
