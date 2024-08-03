import os

from smashfinance_common.common.decorators import singleton
import yaml


@singleton
class ConfigurationService:

    def __init__(self):

        current_file_path = os.path.dirname(os.path.realpath(__file__))
        config_file = os.path.join(current_file_path, 'config.yaml')

        with open(config_file, 'r') as file:
            self._configuration_file = yaml.safe_load(file)

    def get_configuration_file(self):
        return self._configuration_file

    def get_pagination_config(self):
        return self._configuration_file['pagination']
