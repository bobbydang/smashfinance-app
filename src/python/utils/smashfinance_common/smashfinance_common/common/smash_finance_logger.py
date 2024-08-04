

import logging
import logging.config
import os
from pathlib import Path

from smashfinance_common.common.decorators import singleton
import yaml


@singleton
class SmashFinanceLogger:

    _is_configured = False

    def __init__(self):
        self._configure_logger()

    def _configure_logger(self):
        if not self._is_configured:
            log_dir = Path(__file__).resolve(
            ).parent.parent.parent.parent / 'logs'

            config_dir = Path(__file__).resolve().parent.parent / 'config'

            log_config_path = f"{config_dir}/logging_config.yaml"

            try:
                with open(log_config_path, 'r') as f:
                    config = yaml.safe_load(f)
            except FileNotFoundError:
                raise RuntimeError(
                    f"Logging configuration file not found: {log_config_path}")
            except yaml.YAMLError as e:
                raise RuntimeError(
                    f"Error parsing logging configuration file: {e}")

            log_file_path = os.getenv('LOG_FILE_PATH', f"{log_dir}/")

            # Update the log file path in the configuration
            if 'handlers' in config:
                for handler in config['handlers'].values():
                    if handler.get('class') == 'logging.handlers.RotatingFileHandler':
                        handler['filename'] = log_file_path + \
                            "/smashfinance_app_python.log"

            # Apply logging configuration
            logging.config.dictConfig(config)

            self._is_configured = True

    def get_logger(self, __name__):
        return logging.getLogger(__name__)
