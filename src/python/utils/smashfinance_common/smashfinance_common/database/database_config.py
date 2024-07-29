# captialize name below
import os
from pathlib import Path
from dotenv import load_dotenv

# Determine the environment mode
mode = os.getenv("PYTHON_ENV", "prod")
base_dir = Path(__file__).resolve().parent.parent.parent

# Load the appropriate .env file based on the mode
if mode == "test":
    load_dotenv(dotenv_path=os.path.join(base_dir, '.env.test'))
elif mode == "dev":
    load_dotenv(dotenv_path=os.path.join(base_dir, '.env.dev'))
else:
    load_dotenv(dotenv_path=os.path.join(base_dir, '.env.prod'))

target_db_name = "postgres"
db_user = "postgres"
db_pass = os.getenv("DB_PASSWORD")
db_host = "localhost"  # or your database host
db_port = "5432"
cache_expire_after = 3600  # seconds

engine_url = (
    f"postgresql+psycopg2://{db_user}:{db_pass}@{db_host}:{db_port}/{os.getenv('DB_NAME')}"
)
