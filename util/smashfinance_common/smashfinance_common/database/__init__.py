from smashfinance_common.utils.dynamic_import import dynamic_import
from .database import Base, create_engine, dev_engine, test_engine, engine_url_dev, engine_url_test

all_classes, __all__ = dynamic_import(__name__)

globals().update(all_classes)

__all__ = set(all_classes.keys())


database_global = ['Base', 'create_engine', 'dev_engine',
                   'test_engine', 'engine_url_dev', 'engine_url_test']


__all__ = __all__.union(database_global)
