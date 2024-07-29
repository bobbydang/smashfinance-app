
from smashfinance_common.database.database_util import DatabaseUtil
from smashfinance_common.repository.base_repository import BaseRepository
from sqlalchemy.orm import sessionmaker


class ExponentialMovingAverageRepository(BaseRepository):

    def __init__(self):
        super().__init__()
