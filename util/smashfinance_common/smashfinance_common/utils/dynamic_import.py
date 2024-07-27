import importlib
from types import ModuleType
import pkgutil


def dynamic_import(package_name):

    def import_submodules(package_name):
        """ Import all submodules of a module, recursively """
        package = importlib.import_module(package_name)
        results = {}
        for loader, name, is_pkg in pkgutil.walk_packages(package.__path__):
            full_name = package.__name__ + '.' + name
            results[full_name] = importlib.import_module(full_name)
            if is_pkg:
                results.update(import_submodules(full_name))
        return results

    # Import all submodules
    imported_modules = import_submodules(package_name)

    # Create a dictionary to store all classes
    all_classes = {}

    # Iterate through imported modules and add classes to all_classes
    for module_name, module in imported_modules.items():
        for attribute_name in dir(module):
            attribute = getattr(module, attribute_name)
            if isinstance(attribute, type):
                all_classes[attribute_name] = attribute

    # Optionally, you can create an __all__ list for explicit exports
    __all__ = list(all_classes.keys())

    return all_classes, __all__
