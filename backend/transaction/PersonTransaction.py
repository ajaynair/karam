import pdb
from MySql import my_sql
from mysql.connector import Error

from MySql.my_sql import mysql_get_connection
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
                colValues = "VALUES (LAST_INSERT_ID(),%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                sql = statement + colNames + colValues
                val = (laborer.getParentId(), laborer.getFirstname(),
                       laborer.getLastname(), laborer.getGender(), laborer.getPhoneNumber(),
                       laborer.getAddress(), laborer.getAadharNo(), laborer.getAadharStatus(),
                       laborer.getPanCard(), laborer.getSkill(), laborer.getActiveInd(),
                       laborer.getPrefLoc())
                print (val)
                cursor.execute(sql, val)
                connection.commit()

                skills = laborer.getSkill().split(",")
                # Insert into skill table
                for skill in skills:
                    #TODO refactor this code to use select only once
                    sql = "select count(name) from skills where name=(%s)"
                    val = (skill,)
                    cursor.execute(sql,val)
                    res = cursor.fetchone()
                    if int(res[0]) == 0:
                        sql = "Insert into karamdb.skills (name,description) VALUES (%s,%s)"
                        val = (skill,"")
                        cursor.execute(sql,val)
                        connection.commit()

                    sql = "select name from skills where name=(%s)"
                    val = (skill,)
                    cursor.execute(sql,val)
                    res = cursor.fetchone()
                    skillName = res[0]
                    sql = "Insert into karamdb.laborerSkillRelation (laborer_id, skill_name) VALUES (%s,%s)"
                    val = (laborer.getLaborerId(), skillName)
                    cursor.execute(sql,val)
                    connection.commit()

                locations = laborer.getPrefLoc().split(",")
                # Insert into skill table
                for loc in locations:
                    #TODO refactor this code to use select only once
                    sql = "select count(*) from preferredJobLocation where STATE= %s and city = %s and district = %s"
                    val = (loc, loc, loc)
                    cursor.execute(sql,val)
                    res = cursor.fetchone()
                    if int(res[0]) == 0:
                        sql = "Insert into karamdb.preferredJobLocation (STATE,CITY,district) VALUES (%s,%s,%s)"
                        val = (loc,loc,loc)
                        cursor.execute(sql,val)
                        connection.commit()

                    sql = "select id from preferredJobLocation where STATE= %s and city = %s and district = %s"
                    val = (loc, loc, loc)
                    cursor.execute(sql,val)
                    res = cursor.fetchone()
                    locId = int(res[0])
                    sql = "Insert into karamdb.laborerPreferredLocationRelation (laborer_id, location_id) VALUES (%s,%s)"
                    val = (laborer.getLaborerId(), locId)
                    cursor.execute(sql,val)
                    connection.commit()

                print("Inserted successfully in laborer table")
                return "SUCCESS"

        except Error as e:

            print("Error while inserting into laborer, skill, laborerSkillRelation  table", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                #connection.close()
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
                #print("Updated successfully laborer table for " + laborer.getLaborerId())
                return "SUCCESS"

        except Error as e:
            print("Error while connecting to MySQL", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                #connection.close()

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

                sql = "select * from karamdb.laborer"
                if skills and not locations:

                    #TODO this is not good for scalability we need to have SkillEq,SkillLike,SkillIn operation in JSON Request
                    # that will map to differetn sql querries here. Even if we are using OOP sql builder
                    sql = "select * from karamdb.laborer where laborer_id in ("
                    sql = sql + "select laborer_id from laborerSkillRelation where"
                    skillNames = skills.split(',')
                    values = []
                    for skillName in skillNames:
                        sql = sql + " skill_name like %s " + "OR"
                        values.append("%"+skillName+"%")
                    sql = sql[:-2]
                    sql = sql + ")"
                    cursor.execute(sql, values)
                elif locations and not skills:
                    sql = "select * from karamdb.laborer where laborer_id in ("
                    sql = sql + "select laborer_id from laborerPreferredLocationRelation where  location_id in ("
                    sql = sql + "select id from preferredJobLocation where"
                    locationNames = locations.split(',')
                    values = []
                    for locationName in locationNames:
                        sql = sql + " state like %s " + "OR"
                        values.append("%"+locationName+"%")
                    sql = sql[:-2]
                    sql = sql + "))"
                    cursor.execute(sql, values)
                elif locations and skills:
                    # TODO make above code modular so that it can be resused for this case
                    sql = "select * from karamdb.laborer where laborer_id in ("
                    sql = sql + "select labSkill.laborer_id from laborerSkillRelation labSkill  INNER JOIN\
                                (select laborer_id from laborerPreferredLocationRelation where location_id in\
                                 (select id from preferredJobLocation where"
                    locationNames = locations.split(',')

                    values = []
                    for locationName in locationNames:
                        sql = sql + " state like %s " + "OR"
                        values.append("%"+locationName+"%")
                    sql = sql[:-2]
                    sql = sql + "))"

                    sql = sql + " labLoc ON labSkill.laborer_id = labLoc.laborer_id where "
                    skillNames = skills.split(',')
                    for skillName in skillNames:
                        sql = sql + " labSkill.skill_name like %s " + "OR"
                        values.append("%"+skillName+"%")
                    sql = sql[:-2]
                    sql = sql + ")"
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
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                #connection.close()

    def getAllLaborer(self, skills, locations):
        future = th.executor.submit(self.getAllLaborerTask, skills, locations)
        return future.result()


    def getFriendOfLaborerTask(self, parentId):
        connection = mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "SELECT * FROM karamdb.laborer where laborer_id = " + parentId + " or parent_id = " + parentId
                cursor.execute(sql)

                row_headers=[x[0] for x in cursor.description]
                rec = cursor.fetchall()
                json_data=[]
                for res in rec:
                    json_data.append(dict(zip(row_headers,res)))
                return json_data
        except Error as e:
            print("Error while connecting to MySQL", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                #connection.close()

    def getFriendOfLaborer(self, parentId):
        future = th.executor.submit(self.getFriendOfLaborerTask, parentId)
        return future.result()

    def createContractorTask(self,contractor):
        connection = mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                statement = "Insert into karamdb.contractor"
                colNames = "(contractor_id, first_name, phone_number, address)"
                colValues = "VALUES (%s,%s,%s,%s)"
                sql = statement + colNames + colValues
                # TODO how to make it multiline
                val = (
                contractor.getContractorId(), contractor.getFirstname(),
                contractor.getPhoneNumber(), contractor.getAddress())
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
                #connection.close()

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
                #connection.close()

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
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                #connection.close()

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
                colNames = "(role_type,user_name,password_hash)"
                colValues = "VALUES (%s,%s,%s)"
                sql = statement+colNames+colValues
                val = (user.getRoleType(),user.getUserName(),user.getPasswordHash())
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
                #connection.close()

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
                colNames = "(labour_id,contractor_id,active_ind)"
                colValues = "VALUES (%s,%s,%s)"
                sql = statement+colNames+colValues
                val = (job.getLaborerId(),job.getContractorId(),job.getActiveInd())
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
                #connection.close()

    def createUser(self, user):
        future = th.executor.submit(self.createUserTask, user)
        return future.result()

    def getNewUserId(self):
        connection = mysql_get_connection()
        try:
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "SELECT max(user_id) FROM karamdb.user"
                cursor.execute(sql)
                result = cursor.fetchone()
                return int(result[0])
        except Error as e:
            print("Error while connecting to MySQL", e)
            return str(e)
        finally:
            if (connection.is_connected()):
                cursor.close()
                #connection.close()

    def deleteById(self, id):
        future = th.executor.submit(self.deleteByIdTask, id)
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
                #connection.close()
                print("MySQL connection is closed")

    def getUserByCredTask(self, username, password):
        try:
            connection = my_sql.mysql_get_connection()
            if connection.is_connected():
                db_Info = connection.get_server_info()
                print("Connected to MySQL Server version ", db_Info)
                cursor = connection.cursor()
                sql = "select user_id, role_type from karamdb.user where user_name = %s and password_hash = %s"
                val = (username, password)
                cursor.execute(sql, val)
                resp = cursor.fetchall()

                if len(resp) != 0:
                    return resp[0][0], resp[0][1]
                return -1, -1

        except Error as e:
            print("Error while connecting to MySQL", e)
        finally:
            if connection.is_connected():
                cursor.close()
                #connection.close()
                print("MySQL connection is closed")

    def getUserByCred(self, username, password):
        future = th.executor.submit(self.getUserByCredTask, username, password)
        return future.result()

    def getPersonByIdTask(self, id):
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
                #connection.close()
                print("MySQL connection is closed")

    def getAllPersonById(self, id):
        future = th.executor.submit(self.getPersonByIdTask, id)
        return future.result()

    def get_autoincrement_id(self):
        connection = my_sql.mysql_get_connection()
        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            sql = "select LAST_INSERT_ID()"
            cursor.execute(sql)
            return cursor.fetchall()[0][0]

# print(createLaborer("DEF","","R","P","M","9819888888","address","adharNo123","Y","panCardNo123","skill123","Y","DELHI"))
