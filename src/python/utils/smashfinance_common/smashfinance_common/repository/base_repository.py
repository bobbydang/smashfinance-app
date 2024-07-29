from contextlib import contextmanager
from smashfinance_common.database.database_util import DatabaseUtil
from sqlalchemy.orm import sessionmaker


class BaseRepository:
    def __init__(self):

        self._engine = DatabaseUtil.DB_ENGINE
        self._base = DatabaseUtil.BASE
        self._base.metadata.create_all(self._engine, checkfirst=True)
        self._SessionFactory = sessionmaker(bind=self._engine)
        self._session = None

    @contextmanager
    def session_scope(self):
        """Provide a transactional scope around a series of operations."""
        session = self._SessionFactory()
        try:
            yield session
            session.commit()
        except Exception:
            session.rollback()
            raise
        finally:
            session.close()

    def get(self, model, id):
        with self.session_scope() as session:
            return session.query(model).filter(model.id == id).first()

    def get_all(self, model):
        with self.session_scope() as session:
            return session.query(model).all()

    def create(self, model):
        with self.session_scope() as session:
            session.add(model)
            session.refresh(model)
            return model

    def update(self, model, data):
        with self.session_scope() as session:
            for key, value in data.items():
                setattr(model, key, value)
            session.refresh(model)
            return model

    def delete(self, model):
        with self.session_scope() as session:
            session.delete(model)
            return model
