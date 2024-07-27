from smashfinance_common.utils.dynamic_import import dynamic_import

from .database import Base, create_engine, dev_engine, test_engine, engine_url_dev, engine_url_test


database_global = ['Base', 'create_engine', 'dev_engine',
                   'test_engine', 'engine_url_dev', 'engine_url_test']


all_classes, __all__ = dynamic_import(__name__)

globals().update(all_classes)

__all__ += database_global
