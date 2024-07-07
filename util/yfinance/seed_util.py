import yaml


class SeedUtil:

    # get stock company data from yaml file
    @staticmethod
    def get_stock_data():

        with open("./assets/stocks.yml", "r") as file:
            return yaml.safe_load(file)
