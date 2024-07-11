from requests import Session
from requests_cache import CacheMixin
from requests_ratelimiter import LimiterMixin


class CachedLimiterSession(CacheMixin, LimiterMixin, Session):
    pass
