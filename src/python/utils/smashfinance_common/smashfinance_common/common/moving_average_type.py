
from sqlalchemy import Enum


# 0: Simple Moving Average (SMA)
# 1: Exponential Moving Average (EMA)
# 2: Weighted Moving Average (WMA)
# 3: Double Exponential Moving Average (DEMA)
# 4: Triple Exponential Moving Average (TEMA)
# 5: Triangular Moving Average (TRIMA)
# 6: Kaufman Adaptive Moving Average (KAMA)
# 7: MESA Adaptive Moving Average (MAMA)
# 8: Triple Exponential Moving Average (T3)

class MovingAverageType(Enum):
    SMA = 0
    EMA = 1
    WMA = 2
    DEMA = 3
    TEMA = 4
    TRIMA = 5
    KAMA = 6
    MAMA = 7
    T3 = 8
