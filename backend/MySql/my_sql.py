import mysql.connector
from config import config

config.conf_init()

userName = config.get_mysql_username()
userPassword = config.get_mysql_pwd()
hostURL = config.get_mysql_host()
dbName = config.get_mysql_db()


def mysql_get_connection():
    connection = mysql.connector.connect(host=hostURL,
                                         database=dbName,
                                         user=userName,
                                         password=userPassword,
                                         auth_plugin='mysql_native_password')
    return connection
