from configparser import ConfigParser
import os

from config.error import ConfigError

conf_parser: ConfigParser = ConfigParser()
conf_path: str


def conf_init(path: str = 'config_files/config.cfg') -> None:
    global conf_path, conf_parser

    conf_path = path
    if not os.path.exists(conf_path):
        raise ConfigError(path + ' not found.')
    conf_parser.read(conf_path)


def get_mysql_username() -> str:
    return conf_parser.get('mysql_config', 'userName')


def get_mysql_pwd() -> str:
    return conf_parser.get('mysql_config', 'password')


def get_mysql_host() -> str:
    return conf_parser.get('mysql_config', 'host')


def get_mysql_db() -> str:
    return conf_parser.get('mysql_config', 'database')
