from contextlib import contextmanager
from smashfinance_common.common.smash_finance_logger import SmashFinanceLogger
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
        """Provide a transactional scope around a series of operations.

           This context manager creates a session using the _SessionFactory and
           yields it to the caller. After the caller is done with the session,
           the changes are committed. If an exception occurs during the operations,
           the changes are rolled back and the exception is re-raised. Finally,
           the session is closed.

           Usage:
           ```
           with self.session_scope() as session:
               # Perform database operations using the session
           ```

           Returns:
           A session object for performing database operations.
       """
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
        """
        Retrieve a record from the database by its ID.

        Args:
            model: The model class representing the database table.
            id: The ID of the record to retrieve.

        Returns:
            The retrieved record, or None if no record is found.
        """
        with self.session_scope() as session:
            return session.query(model).filter(model.id == id).first()

    def get_all(self, model):
        """
        Retrieve all instances of the specified model from the database.

        Args:
            model: The model class to query.

        Returns:
            A list of all instances of the specified model.
        """
        with self.session_scope() as session:
            return session.query(model).all()

    def create(self, model, unique_fields=None):
        """
        Creates a new model instance and adds it to the database.

        Args:
            model: The model instance to be created.
            unique_fields: A list of field names that should be unique for the model.
            commit: A boolean indicating whether to commit the changes to the database.

        Returns:
            None if a model with the same unique fields already exists, otherwise None.

        """
        model_class = type(model)
        with self.session_scope() as session:
            if unique_fields:
                filters = {field: getattr(model, field)
                           for field in unique_fields}
                existing_model = session.query(
                    model_class).filter_by(**filters).first()
                if existing_model:
                    return

            session.add(model)

    def update(self, model, data):
        """
        Updates the given model with the provided data.

        Args:
            model: The model object to be updated.
            data: A dictionary containing the updated attribute values.

        Returns:
            The updated model object.
        """
        with self.session_scope() as session:
            if not session.contains(model):
                model = session.merge(model)

            for key, value in data.items():
                setattr(model, key, value)
                session.refresh(model)

        return model

    def delete(self, model):
        """
        Deletes the given model from the database.

        Args:
            model: The model object to be deleted.

        Returns:
            The deleted model object.
        """
        with self.session_scope() as session:
            session.delete(model)

        return model
