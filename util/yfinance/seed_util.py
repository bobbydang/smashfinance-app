import yaml


class SeedUtil:

    # get stock company data from yaml file
    @staticmethod
    def get_stock_company_data():

        with open("./assets/stock_company.yml", "r") as file:
            return yaml.safe_load(file)
