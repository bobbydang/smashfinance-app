import os
import subprocess


def append_to_bashrc(env_var, comment):
    bashrc_path = os.path.expanduser("~/.bashrc")

    # Check if the environment variable is already set
    with open(bashrc_path, 'r') as file:
        lines = file.readlines()
        if any(env_var in line for line in lines):
            print(f"{env_var} is already set in {bashrc_path}")
            return

    # Append the environment variable to .bashrc
    with open(bashrc_path, 'a') as file:
        file.write(f"\n# {comment}\nexport {env_var}\n")
    print(f"Added {env_var} to {bashrc_path}")


def create_log_file_path_export():
    env_var = "LOG_FILE_PATH=/var/log/smashfinance-app/"
    comment = "Set log file path for smashfinance-app"
    append_to_bashrc(env_var, comment)


def create_python_env_export():
    env_var = "PYTHON_ENV=dev"
    comment = "Set python env path"
    append_to_bashrc(env_var, comment)


def create_log_directory():
    log_dir = "/var/log/smashfinance-app"

    # Create the directory if it doesn't exist
    if not os.path.exists(log_dir):
        os.makedirs(log_dir)
        print(f"Created directory: {log_dir}")

    # Set read and write permissions for everyone
    subprocess.run(["sudo", "chmod", "a+rw", log_dir])
    print(f"Set read and write permissions for everyone on {log_dir}")


if __name__ == "__main__":
    create_log_file_path_export()
    create_python_env_export()
    create_log_directory()
    print("Setup completed. Please restart your terminal or run 'source ~/.bashrc' to apply the changes.")
