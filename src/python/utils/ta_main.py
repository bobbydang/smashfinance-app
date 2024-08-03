

from technical_analysis.services.ema_indicator_service import EmaIndicatorService


def main():
    ema_service = EmaIndicatorService()
    stock_symbol = "AMD"
    period = 14

    ema_values = ema_service.calculate_ema(stock_symbol, period)
    print(f"EMA values for {stock_symbol} with period {period}:")
    print(ema_values)


if __name__ == "__main__":
    main()
