import configparser
import os

config = configparser.ConfigParser()
config_path = None


def conf_init(path='config/config.cfg'):
    global config_path, config

    config_path = path
    assert os.path.exists(config_path)
    config.read(config_path)


def get_mysql_username():
    return config.get('mysql_config', 'userName')


def get_mysql_pwd():
    return config.get('mysql_config', 'password')


def get_mysql_host():
    return config.get('mysql_config', 'host')


def get_mysql_db():
    return config.get('mysql_config', 'database')


if __name__ == "__main__":
    # Test
    conf_init()
    print("DB Username " + get_mysql_username())
    print("DB password " + get_mysql_pwd())
    print("DB host " + get_mysql_host())
    print("DB name " + get_mysql_db())
