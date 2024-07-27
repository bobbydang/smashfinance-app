from .utils.dynamic_import import dynamic_import
from .database import __all__ as database_all

# Dynamic import for the current package
all_classes, dynamic_all = dynamic_import(__name__)

globals().update(all_classes)

# Combine dynamic_all and database_all without duplicates
__all__ = list(set(dynamic_all).union(database_all))
