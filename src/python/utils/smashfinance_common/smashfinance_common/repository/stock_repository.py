
from smashfinance_common.repository.base_repository import BaseRepository


class StockRepository(BaseRepository):
    def __init__(self):
        super().__init__()

    def add_stock(self, stock):
        self.create(stock)
