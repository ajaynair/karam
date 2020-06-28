import mysql.connector
from mysql.connector import Error
import configparser
import os
from ThreadExecutor import ThreadExecutor

config = configparser.ConfigParser()
url = 'C:\\config.txt'
assert os.path.exists(url)
config.read(url)
userName = config.get('configuration','userName').strip('"')
userPassword = config.get('configuration','password').strip('"')
hostURL = config.get('configuration','host').strip('"')
dbName = config.get('configuration','database').strip('"')

th = ThreadExecutor.instance()

def create(id,fname,lname,gender,phno,address,adharNo,adharStatus,panCardNo):
    future = th.executor.submit(createTask,id,fname,lname,gender,phno,address,adharNo,adharStatus,panCardNo)
    return future.result()

def createTask(id,fname,lname,gender,phno,address,adharNo,adharStatus,panCardNo):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            statement = "Insert into karamdb.person"
            colNames = "(person_id, first_name, last_name, gender, phone_number, address,adhar_card_number,adhar_card_status,pan_card)"
            colValues = "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            sql = statement+colNames+colValues

            val = (id,fname,lname,gender,phno,address,adharNo,adharStatus,panCardNo)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")


def deleteById(id):
    future = th.executor.submit(deleteByIdTask,id)
    return future.result()

def deleteByIdTask(id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "delete from karamdb.person where person_id = %s"
            val = (id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updateFirstName(firstName, id):
    future = th.executor.submit(updateFirstNameTask,firstName,id)
    return future.result()

def updateFirstNameTask(firstName, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set first_name = %s where person_id = %s"
            val = (firstName,id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updateLastName(lastName, id):
    future = th.executor.submit(updateLastNameTask,lastName,id)
    return future.result()

def updateLastNameTask(lastName, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set last_name = %s where person_id = %s"
            val = (lastName,id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updatePhoneNumber(phoneNumber, id):
    future = th.executor.submit(updatePhoneNumberTask,phoneNumber,id)
    return future.result()

def updatePhoneNumberTask(phoneNumber, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set phone_number = %s where person_id = %s"
            val = (phoneNumber, id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updateAddress(address, id):
    future = th.executor.submitt(updateAddressTask,address,id)
    return future.result()

def updateAddressTask(address, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set ADDRESS = %s where person_id = %s"
            val = (address, id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updatePanCard(panCard, id):
    future = th.executor.submit(updatePanCardTask,panCard,id)
    return future.result()

def updatePanCardTask(panCard, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set pan_card = %s where person_id = %s"
            val = (panCard, id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updateAdharCardNumber(adharCardNumber, id):
    future = th.executor.submit(updateAdharCardNumberTask,adharCardNumber,id)
    return future.result()

def updateAdharCardNumberTask(adharCardNumber, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set adhar_card_number = %s where person_id = %s"
            val = (adharCardNumber, id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def updateAdharCardStatus(status, id):
    future = th.executor.submit(updateAdharCardStatusTask,status,id)
    return future.result()

def updateAdharCardStatusTask(status, id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "update karamdb.person set adhar_card_status = %s where person_id = %s"
            val = (status, id,)
            cursor.execute(sql,val)
            connection.commit()
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def getAllPerson():
    future = th.executor.submit(getAllPersonTask)
    return future.result()

def getAllPersonTask():
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "select * from karamdb.person"
            cursor.execute(sql);
            rec = cursor.fetchall()
            result = list()
            for x in rec:
                result.append(x)
            return result
    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

def getAllPersonById(id):
    future = th.executor.submit(getPersonByIdTask,id)
    return future.result()

def getPersonByIdTask(id):
    try:
        connection = mysql.connector.connect(host=hostURL,
                                             database=dbName,
                                             user=userName,
                                             password=userPassword)
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "select * from karamdb.person where person_id = %s"
            val = (id,)
            cursor.execute(sql,val)
            rec = cursor.fetchall()
            result = list()
            for x in rec:
                result.append(x)
            return result
    except Error as e:
        print("Error while connecting to MySQL", e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

print(getAllPerson())
