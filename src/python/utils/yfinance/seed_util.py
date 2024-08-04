from pathlib import Path
import yaml


class SeedUtil:

    # get stock company data from yaml file
    @staticmethod
    def get_stock_data():

        base_dir = Path(__file__).resolve().parent.parent
        with open(f"{base_dir}/assets/stocks.yml", "r") as file:
            return yaml.safe_load(file)
