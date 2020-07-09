from MySql import my_sql
from mysql.connector import Error
from utils.ThreadExecutor import ThreadExecutor

# TODO Give example request body and response body
'''
FIXME do not hardcode MySql queries. Database name and table names can be
defined as global variables or class members. It will make it easier to change 
table names and database names.
'''
'''
FIXME Check if creating and closing connections after every MySql query is a bad idea.
Can we create a pool of connections?
'''


class PersonTransaction:
    def __init__(self):
        pass

    # TODO check why having global keyword is necessary
    global th
    th = ThreadExecutor.instance()

    def createLaborerTask(self, laborer):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.laborer"
                colNames = "(laborer_id,parent_id, first_name, last_name, gender, phone_number, address,aadhar_card_number,aadhar_card_status,pan_card,skills,active_ind,preferred_job_location)"
                colValues = "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                sql = statement + colNames + colValues
                val = (laborer.getLaborerId(), laborer.getParentId(), laborer.getFirstname(),
                       laborer.getLastname(), laborer.getGender(), laborer.getPhoneNumber(),
                       laborer.getAddress(), laborer.getAadharNo(), laborer.getAadharStatus(),
                       laborer.getPanCard(), laborer.getSkill(), laborer.getActiveInd(),
                       laborer.getPrefLoc())
                print (val)
                cursor.execute(sql, val)
                connection.commit()
                print("Inserted successfully in laborer table")
                return "SUCCESS"

        except Error as e:
            print("Error while inserting into laborer table", e)
            '''
            FIXME Create a json message not a string
            '''
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def createLaborer(self, laborer):
        future = th.executor.submit(self.createLaborerTask, laborer)
        return future.result()

    def updateLaborerTask(self, laborer):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "update karamdb.laborer set "
                values = []
                if (laborer.getFirstname()):
                    sql += "first_name = %s,"
                    values.append(laborer.getFirstname())
                if (laborer.getLastname()):
                    sql += "last_name = %s,"
                    values.append(laborer.getLastname())
                if (laborer.getPhoneNumber()):
                    sql += "phone_number = %s,"
                    values.append(laborer.getPhoneNumber())
                if (laborer.getAddress()):
                    sql += "address = %s,"
                    values.append(laborer.getAddress())
                if (laborer.getAadharStatus()):
                    sql += "adhar_card_status = %s,"
                    values.append(laborer.getAadharStatus())
                if (laborer.getAadharNo()):
                    sql += "adhar_card_number = %s,"
                    values.append(laborer.getAadharNo())
                if (laborer.getPanCard()):
                    sql += "pan_card = %s,"
                    values.append(laborer.getPanCard())
                if (laborer.getSkill()):
                    sql += "skill = %s,"
                    values.append(laborer.getSkill())
                if (laborer.getActiveInd()):
                    sql += "active_ind = %s,"
                    values.append(laborer.getActiveInd())
                if (laborer.getPrefLoc()):
                    sql += "preferred_job_location = %s,"
                    values.append(laborer.getPrefLoc())

                sql = sql.rstrip(',')
                print(sql)
                print(values)
                cursor.execute(sql, values)
                connection.commit()
                print("Updated successfully laborer table for " + laborer.getLaborerId())
                return "SUCCESS"

        except Error as e:
            print("Error while connecting to MySQL", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def updateLaborer(self, laborer):
        future = th.executor.submit(self.updateLaborerTask, laborer)
        return future.result()

    def getAllLaborerTask(self, skills, locations):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()

                sql = "SELECT * FROM karamdb.laborer"
                if skills and not locations:
                    sql = sql + " where skill in ({list})".format(
                        list=','.join(['%s'] * len(skills)))
                    cursor.execute(sql, skills)
                elif locations and not skills:
                    sql = sql + " where preferred_job_location in ({list})".format(
                        list=','.join(['%s'] * len(locations)))
                    cursor.execute(sql, locations)
                elif locations and skills:
                    sql = sql + " where preferred_job_location in ({list})".format(
                        list=','.join(['%s'] * len(locations)))
                    sql = sql + " and skill in ({list})".format(list=','.join(['%s'] * len(skills)))
                    values = []
                    for loc in locations:
                        values.append(loc)
                    for skill in skills:
                        values.append(skill)
                    cursor.execute(sql, values)
                else:
                    cursor.execute(sql)

                row_headers = [x[0] for x in cursor.description]
                rec = cursor.fetchall()
                json_data = []
                for res in rec:
                    json_data.append(dict(zip(row_headers, res)))
                print (json_data)
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

    def createContractorTask(self, contractor):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.contractor"
                colNames = "(contractor_id,parent_id, first_name, last_name, gender, phone_number, address,adhar_card_number,adhar_card_status,pan_card,skill,active_ind,preferred_job_location)"
                colValues = "VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                sql = statement + colNames + colValues
                # TODO how to make it multiline
                val = (
                contractor.getContractorId(), contractor.getParentId(), contractor.getFirstname(),
                contractor.getLastname(), contractor.getGender(), contractor.getPhoneNumber(),
                contractor.getAddress(), contractor.getAadharNo(), contractor.getAadharStatus(),
                contractor.getPanCard(), contractor.getSkill(), contractor.getActiveInd(),
                contractor.getPrefLoc())
                cursor.execute(sql, val)
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

    # TODO Update rest of MySql functions like create, they are referring to old table schema
    def createContractor(self, contractor):
        future = th.executor.submit(self.createContractorTask, contractor)
        return future.result()

    def updateContractorTask(self, contractor):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "update karamdb.contractor set "
                values = []
                if (contractor.getFirstname()):
                    sql += "first_name = %s,"
                    values.append(contractor.getFirstname())
                if (contractor.getLastname()):
                    sql += "last_name = %s,"
                    values.append(contractor.getLastname())
                if (contractor.getPhoneNumber()):
                    sql += "phone_number = %s,"
                    values.append(contractor.getPhoneNumber())
                if (contractor.getAddress()):
                    sql += "address = %s,"
                    values.append(contractor.getAddress())
                if (contractor.getAadharStatus()):
                    sql += "adhar_card_status = %s,"
                    values.append(contractor.getAadharStatus())
                if (contractor.getAadharNo()):
                    sql += "adhar_card_number = %s,"
                    values.append(contractor.getAadharNo())
                if (contractor.getPanCard()):
                    sql += "pan_card = %s,"
                    values.append(contractor.getPanCard())
                if (contractor.getSkill()):
                    sql += "skill = %s,"
                    values.append(contractor.getSkill())
                if (contractor.getActiveInd()):
                    sql += "active_ind = %s,"
                    values.append(contractor.getActiveInd())
                if (contractor.getPrefLoc()):
                    sql += "preferred_job_location = %s,"
                    values.append(contractor.getPrefLoc())

                sql = sql.rstrip(',')
                cursor.execute(sql, values)
                connection.commit()
                print("Updated successfully contractor table for " + contractor.getContractorId())
                return "SUCCESS"

        except Error as e:
            print("Error while connecting to MySQL", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

    def updateContractor(self, contractor):
        future = th.executor.submit(self.updateContractorTask, contractor)
        return future.result()

    def getAllContractorTask(self):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "SELECT * FROM karamdb.contractor"
                cursor.execute(sql)
                row_headers = [x[0] for x in cursor.description]
                rec = cursor.fetchall()
                json_data = []
                for res in rec:
                    json_data.append(dict(zip(row_headers, res)))
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

    def createUserTask(self, user):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.user"
                colNames = "(user_id,role_type,password_hash)"
                colValues = "VALUES (%s,%s,%s)"
                sql = statement + colNames + colValues
                val = (user.getUserId(), user.getRoleType(), user.getPasswordHash())
                cursor.execute(sql, val)
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

    def createJob(self, job):
        future = th.executor.submit(self.createJobTask, job)
        return future.result()

    def createJobTask(self, job):
        connection = my_sql.mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.job"
                colNames = "(job_id,labour_id,contractor_id,active_ind)"
                colValues = "VALUES (%s,%s,%s,%s)"
                sql = statement + colNames + colValues
                val = (
                job.getJobId(), job.getLaborerId(), job.getContractorId(), job.getActiveInd())
                cursor.execute(sql, val)
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

    def createUser(self, user):
        future = th.executor.submit(self.createUserTask, user)
        return future.result()

    def deleteById(id):
        future = th.executor.submit(deleteByIdTask, id)
        return future.result()

    def deleteByIdTask(id):
        try:
            connection = my_sql.mysql_get_connection()
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "delete from karamdb.person where person_id = %s"
                val = (id,)
                cursor.execute(sql, val)
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
        future = th.executor.submit(getPersonByIdTask, id)
        return future.result()

    def getPersonByIdTask(id):
        try:
            connection = my_sql.mysql_get_connection()
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "select * from karamdb.person where person_id = %s"
                val = (id,)
                cursor.execute(sql, val)
                rec = cursor.fetchall()
                result = list()
                for x in rec:
                    result.append(x)
                return result
        except Error as e:
            print("Error while connecting to MySQL", e)
        finally:
            if connection.is_connected():
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

# print(createLaborer("DEF","","R","P","M","9819888888","address","adharNo123","Y","panCardNo123","skill123","Y","DELHI"))
