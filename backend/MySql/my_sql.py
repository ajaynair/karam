import mysql.connector
from config import config
'''
Reads config files and manages a common connection object.
'''
config.conf_init()

userName = config.get_mysql_username()
userPassword = config.get_mysql_pwd()
hostURL = config.get_mysql_host()
dbName = config.get_mysql_db()

connection = None
def mysql_get_connection():
    global connection
    if connection == None:
        print ('New connection')
        connection = mysql.connector.connect(host=hostURL,
                                         database=dbName,
                                         user=userName,
                                         password=userPassword,
                                         auth_plugin='mysql_native_password')
    return connection
