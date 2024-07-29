from setuptools import setup, find_packages

with open('requirements.txt') as f:
    requirements = f.read().splitlines()

setup(
    name="smashfinance_common",
    version="0.0.2",
    packages=find_packages(exclude=["tests*"]),
    install_requires=requirements,
    include_package_data=True,
    author="Bobby",
    author_email="your.email@example.com",
    description="Smash Finance Common Package",
    long_description=open('README.md').read(),
    long_description_content_type="text/markdown",
    license="MIT",
    platforms=["any"],
    url="https://www.smashfinance.ca",
    classifiers=[
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ],
    python_requires='>=3.6',
)
