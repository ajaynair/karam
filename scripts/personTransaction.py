import mysql.connector
from mysql.connector import Error
import configparser
import os
from datetime import datetime
from ThreadExecutor import ThreadExecutor

class PersonTransaction:
    config = configparser.ConfigParser()
    url = 'C:\\config.txt'
    assert os.path.exists(url)
    config.read(url)
    userName = config.get('configuration','userName').strip('"')
    userPassword = config.get('configuration','password').strip('"')
    hostURL = config.get('configuration','host').strip('"')
    dbName = config.get('configuration','database').strip('"')

    #TODO check why having global keyword is necessary
    global th
    th = ThreadExecutor.instance()

    def createLaborerTask(self,laborer):
        connection = mysql.connector.connect(host=self.hostURL,
                                    database=self.dbName,
                                    user=self.userName,
                                    password=self.userPassword)
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.laborer"
                colNames = "(laborer_id,parent_id, first_name, last_name, gender, phone_number, address,adhar_card_number,adhar_card_status,pan_card,skill,active_ind,preferred_job_location)"
                colValues = "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                sql = statement+colNames+colValues
                val = (laborer.getLaborerId(),laborer.getParentId(),laborer.getFirstname(),laborer.getLastname(),laborer.getGender(),laborer.getPhoneNumber(),laborer.getAddress(),laborer.getAadharNo(),laborer.getAadharStatus(),laborer.getPanCard(),laborer.getSkill(),laborer.getActiveInd(),laborer.getPrefLoc())
                cursor.execute(sql,val)
                connection.commit()
                print("Inserted successfully in laborer table")
                return "SUCCESS"

        except Error as e:
            print("Error while inserting into laborer table", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")


    def createLaborer(self,laborer):
        future = th.executor.submit(self.createLaborerTask,laborer)
        return future.result()

    def getAllLaborerTask(self, skills, locations):
        connection = mysql.connector.connect(host=self.hostURL,
                                             database=self.dbName,
                                             user=self.userName,
                                             password=self.userPassword)
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()

                sql = "SELECT * FROM karamdb.laborer"
                if skills and not locations:
                    sql = sql + " where skill in ({list})".format(list=','.join(['%s']*len(skills)))
                    cursor.execute(sql, skills)
                elif locations and not skills:
                    sql = sql + " where preferred_job_location in ({list})".format(list=','.join(['%s']*len(locations)))
                    cursor.execute(sql, locations)
                elif locations and skills:
                    sql = sql + " where preferred_job_location in ({list})".format(list=','.join(['%s']*len(locations)))
                    sql = sql + " and skill in ({list})".format(list=','.join(['%s']*len(skills)))
                    values = []
                    for loc in locations:
                        values.append(loc)
                    for skill in skills:
                        values.append(skill)
                    cursor.execute(sql, values)
                else:
                    cursor.execute(sql)

                row_headers=[x[0] for x in cursor.description]
                rec = cursor.fetchall()
                json_data=[]
                for res in rec:
                    json_data.append(dict(zip(row_headers,res)))
                return json_data
        except Error as e:
            print("Error while connecting to MySQL", e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def getAllLaborer(self, skills, locations):
        future = th.executor.submit(self.getAllLaborerTask, skills, locations)
        return future.result()

    def createContractorTask(self,contractor):
        connection = mysql.connector.connect(host=self.hostURL,
                                    database=self.dbName,
                                    user=self.userName,
                                    password=self.userPassword)
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.contractor"
                colNames = "(contractor_id,parent_id, first_name, last_name, gender, phone_number, address,adhar_card_number,adhar_card_status,pan_card,skill,active_ind,preferred_job_location)"
                colValues = "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                sql = statement+colNames+colValues
                # TODO how to make it multiline
                val = (contractor.getContractorId(),contractor.getParentId(),contractor.getFirstname(),contractor.getLastname(),contractor.getGender(),contractor.getPhoneNumber(),contractor.getAddress(),contractor.getAadharNo(),contractor.getAadharStatus(),contractor.getPanCard(),contractor.getSkill(),contractor.getActiveInd(),contractor.getPrefLoc())
                cursor.execute(sql,val)
                connection.commit()
                print("Inserted successfully in contractor table")
                return "SUCCESS"

        except Error as e:
            print("Error while inserting into contractor table", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    # TODO Update rest of sql functions like create, they are referring to old table schema
    def createContractor(self,contractor):
        future = th.executor.submit(self.createContractorTask,contractor)
        return future.result()

    def getAllContractorTask(self):
        connection = mysql.connector.connect(host=self.hostURL,
                                             database=self.dbName,
                                             user=self.userName,
                                             password=self.userPassword)
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "SELECT * FROM karamdb.contractor"
                cursor.execute(sql)
                row_headers=[x[0] for x in cursor.description]
                rec = cursor.fetchall()
                json_data=[]
                for res in rec:
                    json_data.append(dict(zip(row_headers,res)))
                return json_data
        except Error as e:
            print("Error while connecting to MySQL", e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def getAllContractor(self):
        future = th.executor.submit(self.getAllContractorTask)
        return future.result()

    def createUserTask(self,user):
        connection = mysql.connector.connect(host=self.hostURL,
                                    database=self.dbName,
                                    user=self.userName,
                                    password=self.userPassword)
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.user"
                colNames = "(user_id,role_type,user_name,password_hash)"
                colValues = "VALUES (%s,%s,%s,%s)"
                sql = statement+colNames+colValues
                val = (user.getUserId(),user.getRoleType(),user.getUserName(),user.getPasswordHash())
                cursor.execute(sql,val)
                connection.commit()
                print("Inserted successfully in user table")
                return "SUCCESS"

        except Error as e:
            print("Error while inserting into user table", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def createJob(self,job):
        future = th.executor.submit(self.createJobTask,job)
        return future.result()

    def createJobTask(self,job):
        connection = mysql.connector.connect(host=self.hostURL,
                                    database=self.dbName,
                                    user=self.userName,
                                    password=self.userPassword)
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.job"
                colNames = "(job_id,labour_id,contractor_id,active_ind)"
                colValues = "VALUES (%s,%s,%s,%s)"
                sql = statement+colNames+colValues
                val = (job.getJobId(),job.getLaborerId(),job.getContractorId(),job.getActiveInd())
                cursor.execute(sql,val)
                connection.commit()
                print("Inserted successfully in job table")
                return "SUCCESS"

        except Error as e:
            print("Error while inserting into user table", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def createUser(self,user):
        future = th.executor.submit(self.createUserTask,user)
        return future.result()

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

#print(createLaborer("DEF","","R","P","M","9819888888","address","adharNo123","Y","panCardNo123","skill123","Y","DELHI"))
