

from pathlib import Path

import yaml
from technical_analysis.services.ema_indicator_service import EmaIndicatorService


def main():
    ema_service = EmaIndicatorService()

    base_dir = Path(__file__).resolve().parent
    with open(f"{base_dir}/assets/stocks.yml", "r") as f:
        stocks = yaml.safe_load(f)

    stock_symbols = stocks.get("stocks")

    for stock_symbol in stock_symbols:
        for period in range(2, 31):
            ema_service.calculate_and_save_ema(
                stock_symbol['tickerSymbol'], period)

    print("Successfully calculated and saved EMA values.")


if __name__ == "__main__":
    main()
