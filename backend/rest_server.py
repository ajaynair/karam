#!flask/bin/python
import os
import pdb

from flask import Flask, jsonify, request
import json
from POJO import LaborerPOJO,ContractorPOJO,UserPOJO,JobPOJO
from transaction import PersonTransaction
import datetime

app = Flask(__name__)

# TODO Validate JSON schema
'''
{
  "filter": {
  "skills": "ELEC",
  "locations": "DELHI"
  }
}
 Takes filters as input and filters the response accordingly
Returns a list of job details
'''
@app.route('/v1.0/person/laborer', methods=['GET'])
def get_laborer_list():
    skills = request.args.get('skills')
    locations = None
    obj1 = PersonTransaction.PersonTransaction()
    return jsonify(obj1.getAllLaborer(skills, locations))

'''
{

}
Returns a list of contractors
'''
@app.route('/v1.0/person/contractor', methods=['GET'])
def get_contractor_list():
    data = json.loads(request.get_data())
    obj1 = PersonTransaction.PersonTransaction()
    return jsonify(obj1.getAllContractor())

'''
{
    "information" :
    {
    "jobId": "JOB123",
    "laborerId": "LAB123",
    "contractorId": "CON123",
    "activeInd": "Y"
    }
}
Creates a job profile for laborer and contractor
'''
@app.route('/v1.0/job/create', methods=['POST'])
def create_job():
    data = json.loads(request.get_data())
    job = JobPOJO.JobPOJO()

    #TODO We need to remove some colums from database maybe
    #TODO check how to do multiline code intendentation in python
    job = job.setLaborerId(data['laborerId']).setContractorId(data['contractorId'])
    job = job.setActiveInd(data['activeInd'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createJob(job)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
{
    "information" :
    {
    "roleType": "L",
    "userName": "USER125",
    "passwordHash": "PASS125"
    }
}
Creates a profile of a user while signup
'''
@app.route('/v1.0/person/signup', methods=['POST'])
def create_user_profile():
    data = json.loads(request.get_data())
    user = UserPOJO.UserPOJO()

    #TODO We need to remove some colums from database maybe
    #TODO check how to do multiline code intendentation in python
    user = user.setRoleType(data['roleType']).setUserName(data['userName'])
    user = user.setPasswordHash(data['passwordHash'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createUser(user)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
{
    "information" :
    {
    "contractorId": "2",
    "parentId": 0,
    "fname": "RIS",
    "lname": "PAN",
    "gender": "M",
    "phno": "981111111",
    "address": "DELHI",
    "aadharStatus": "Y",
    "aadharNumber" : "ADHAE123",
    "panCard" : "PAN123",
    "skill" : "SKILL123",
    "activeInd" : "Y",
    "preferred_location": "DELHI"
    }
}
Creates a profile of a contractor
'''
@app.route('/v1.0/person/contractor', methods=['POST'])
def create_contractor_profile():
    data = json.loads(request.get_data())
    contractor = ContractorPOJO.ContractorPOJO()

    user = UserPOJO.UserPOJO()
    user = user.setRoleType("C").setUserName(data['username']).setPasswordHash(data['password'])

    #TODO We need to remove some columns from database maybe
    #TODO check how to do multiline code intendentation in python
    contractor = contractor.setFirstname(data['fname'])
    contractor = contractor.setPhoneNumber(data['phone_no'])
    contractor = contractor.setAddress(data['location'])

    obj1 = PersonTransaction.PersonTransaction()
    obj1.createUser(user)
    contractor_id = obj1.get_autoincrement_id()
    contractor.setContractorId(contractor_id)
    status = obj1.createContractor(contractor)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
{
    "information" :
    {
    "laborerId": "15",
    "parentId": 0,
    "fname": "RIS",
    "lname": "PAN",
    "gender": "M",
    "phno": "981111111",
    "address": "DELHI",
    "aadharStatus": "Y",
    "aadharNumber" : "ADHAE123",
    "panCard" : "PAN123",
    "skill" : "CARPENTER, PLUMBER, ELECTRICIAN",
    "activeInd" : "Y",
    "preferred_location": "DELHI,MUMBAI,PUNE,BANGLORE"
    }
}
Creates a profile of a laborer
'''
@app.route('/v1.0/person/laborer', methods=['POST'])
def create_laborer_profile():
    data = json.loads(request.get_data())
    laborer = LaborerPOJO.LaborerPOJO()
    user = UserPOJO.UserPOJO()
    user = user.setRoleType("L").setUserName(data["first_name"]).setPasswordHash(data["password"])

    #TODO We need to remove some columns from database maybe
    #TODO check how to do multiline code indentation in python
    laborer = laborer.setParentId(data['parentId']).setFirstname(data['first_name'])
    laborer = laborer.setLastname(data['lname']).setGender(data['gender']).setPhoneNumber(data['phone_number'])
    laborer = laborer.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo(data['aadharNumber'])
    laborer = laborer.setPanCard(data['panCard']).setSkill(data['skills']).setActiveInd(data['activeInd']).setPrefLoc(data['preferred_job_location'])

    obj1 = PersonTransaction.PersonTransaction()
    obj1.createUser(user)
    laborer_id = obj1.get_autoincrement_id()
    laborer.setLaborerId(laborer_id)
    status = obj1.createLaborer(laborer)
    resp = {
        "userId" : obj1.get_autoincrement_id(),
        "status": status
    }

    return jsonify(resp)

'''
{
    "information" :
    {
    "fname": "ADAM",
    "lname": "EVE",
    "gender": "M",
    "phno": "981111111",
    "address": "DELHI",
    "aadharStatus": "Y",
    "aadharNumber" : "ADHAE123",
    "panCard" : "PAN123",
    "skill" : "SKILL100",
    "activeInd" : "Y",
    "preferred_location": "PUNE"
    }
}
Create a laborer profile for a friend
'''
@app.route('/v1.0/person/laborer/<pid>/laborer', methods=['POST'])
def create_friend_profile(pid):
    data = json.loads(request.get_data())

    #INSERT DUMMY ROW IN USER TABLE FOR FRIEND WITH EMPTY USERNAME AND PASSWORD
    obj1 = PersonTransaction.PersonTransaction()
    friendUserID = obj1.getNewUserId()+1
    user = UserPOJO.UserPOJO()
    user = user.setUserId(friendUserID).setRoleType("L").setUserName("").setPasswordHash("")
    obj1.createUser(user)

    laborer = LaborerPOJO.LaborerPOJO()
    laborer = laborer.setLaborerId(friendUserID).setParentId(pid).setFirstname(data['fname'])
    laborer = laborer.setLastname(data['lname']).setGender(data['gender']).setPhoneNumber(data['phno'])
    laborer = laborer.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo(data['aadharNumber'])
    laborer = laborer.setPanCard(data['panCard']).setSkill(data['skill']).setActiveInd(data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createLaborer(laborer)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
{
    "information" :
    {
    "fname": "",
    "lname": "",
    "gender": "",
    "phno": "7777777777",
    "address": "",
    "aadharStatus": "",
    "aadharNumber" : "",
    "panCard" : "",
    "skill" : "NOSKILL",
    "activeInd" : "",
    "preferred_location": "KERALA"
    }
}
Modify profile of a laborer
'''
@app.route('/v1.0/person/laborer/<pid>', methods=['PUT'])
def modify_laborer_profile(pid):
    data = json.loads(request.get_data())

    laborer = LaborerPOJO.LaborerPOJO()
    laborer = laborer.setActiveInd(data['activeInd'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.updateLaborer(laborer)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
{
    "information" :
    {
    "fname": "",
    "lname": "",
    "gender": "",
    "phno": "",
    "address": "",
    "aadharStatus": "",
    "aadharNumber" : "",
    "panCard" : "",
    "skill" : "BUILDING",
    "activeInd" : "",
    "preferred_location": "MAHARASTRA"
    }
}
Modify a profile of a contractor
'''
@app.route('/v1.0/person/contractor/<pid>', methods=['PUT'])
def modify_contractor_profile(pid):
    data = json.loads(request.get_data())

    contractor = ContractorPOJO.ContractorPOJO()
    contractor = contractor.setContractorId(pid).setFirstname(data['fname']).setLastname(data['lname']).setPhoneNumber(data['phno'])
    contractor = contractor.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo(data['aadharNumber'])
    contractor = contractor.setPanCard(data['panCard']).setSkill(data['skill']).setActiveInd(data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.updateContractor(contractor)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
Return list of laborer who are created/friend by/of pid laborer
'''
@app.route('/v1.0/person/laborer/<pid>/laborer', methods=['GET'])
def get_laborer_and_friends(pid):
    obj1 = PersonTransaction.PersonTransaction()
    print(obj1.getFriendOfLaborer(pid))
    return jsonify(obj1.getFriendOfLaborer(pid))


@app.route('/v1.0/person/session', methods=['POST'])
def get_person_session():
    data = json.loads(request.get_data())
    username = data['username']
    password = data['password']
    obj1 = PersonTransaction.PersonTransaction()
    id, role_type = obj1.getUserByCredTask(username, password)
    print (id, role_type)
    resp = {
        "id": id,
        "role_type": role_type
    }
    return jsonify(resp)




##################################################################
# APIs for the future ignore for now
##################################################################
@app.route('/v1.0/person', methods=['DELETE'])
def delete_person():
    data = json.loads(request.get_data())
    pid = data['pid']
    resp = {
        "error": 0
    }

    return jsonify(resp)


@app.route('/v1.0/person/<pid>/job', methods=['DELETE'])
def delete_person_job(pid):
    return "pid"


@app.route('/v1.0/person/<pid>/job/<jid>', methods=['GET'])
def get_person_job_id(pid, jid):
    return "pid"


@app.route('/v1.0/person/<pid>/job/<jid>', methods=['PUT'])
def put_person_job(pid, jid):
    return "pid"


@app.route('/v1.0/person/<pid>/activity_log', methods=['POST'])
def post_person_activity_log(pid):
    return "pid"


@app.route('/v1.0/person/<pid>/setting', methods=['GET'])
def get_person_setting(pid):
    return "pid"


@app.route('/v1.0/person/<pid>/setting', methods=['PUT'])
def put_person_settings(pid):
    return "pid2"


@app.route('/v1.0/person/<pid>/about_me', methods=['GET'])
def get_about_me(pid):
    return "pid"


# /person/<id>
@app.route('/v1.0/person/<pid>', methods=['GET'])
def get_person_details(pid):
    return "pid"


@app.route('/v1.0/person/<pid>', methods=['PUT'])
def put_person_details(pid):
    return "pid"


@app.route('/v1.0/person/<pid>/credential', methods=['PUT'])
def put_person_credential(pid):
    return "pid"
##################################################################
# ^ APIs for the future ignore for now
##################################################################

# Use HTTPS. Use this code in production!
# Generate openssl self-signed certificate to support HTTPS
# using this command:
# openssl req -new -newkey rsa:2048 -nodes -keyout server.key -out server.csr
# The command will generate 2 files server.key and server.csr. Based on the location
# fill these 2 variables
#OPENSSL_CRT_LOC = ''
#OPENSSL_KEY_LOC = ''
#context = ('C:\\Users\\Administrator\\openssl.crt', 'C:\\Users\\Administrator\\openssl.key')
#app.run(debug=False, ssl_context=context)


# Use HTTP. Use this code only for testing!
app.run(debug=True)