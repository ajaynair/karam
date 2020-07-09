#!flask/bin/python
import json
import os
import time
from flask import Flask, jsonify, request

from POJO import LaborerPOJO, ContractorPOJO, UserPOJO, JobPOJO
from transaction import PersonTransaction

app = Flask(__name__)

from random import seed
from random import randint

seed(time.process_time())
userId = randint(0, 100000)

'''
Get a list of people
Request:
 {
  "filter": {
  "skills": [],
  "locations": []
  }
 }
Response:
 {
         {
            "id": 1,
            "Name": "test_name",
            "Age": 20,
            "Gender": "M",
            "Preferred Location": "Pune, Mumbai",
            "Skill": "Carpenter",
            "Contact No": 9923033442,
            "links": {
                "self": request.path + '/1',
                "parent": request.path
            }, {}, {}
 }
 Takes filters as input and filters the response accordingly
Returns a list of job details
'''


@app.route('/v1.0/person/laborer', methods=['GET'])
def get_laborer_list():
    skills = None
    locations = None
    if request.get_data():
        data = json.loads(request.get_data())
        skills = data['filter']['skills']
        locations = data['filter']['locations']
    obj1 = PersonTransaction.PersonTransaction()
    print (obj1.getAllLaborer(skills, locations))
    return jsonify(obj1.getAllLaborer(skills, locations))


'''
Returns a list of contractors
'''


@app.route('/v1.0/person/contractor', methods=['GET'])
def get_contractor_list():
    data = json.loads(request.get_data())
    obj1 = PersonTransaction.PersonTransaction()
    return jsonify(obj1.getAllContractor())


'''
Creates a job profile for laborer and contractor
'''


@app.route('/v1.0/job/create', methods=['POST'])
def create_job():
    data = json.loads(request.get_data())
    job = JobPOJO.JobPOJO()

    # TODO We need to remove some colums from database maybe
    # TODO check how to do multiline code intendentation in python
    job = job.setJobId(data['jobId']).setLaborerId(data['laborerId']).setContractorId(
        data['contractorId'])
    job = job.setActiveInd(data['activeInd'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createJob(job)
    resp = {
        "status": status
    }

    return jsonify(resp)


'''
Creates a profile of a user while signup
'''


@app.route('/v1.0/person/signup', methods=['POST'])
def create_user_profile():
    data = json.loads(request.get_data())
    user = UserPOJO.UserPOJO()

    # TODO We need to remove some colums from database maybe
    # TODO check how to do multiline code intendentation in python
    user = user.setUserId(data['userId']).setRoleType(data['roleType']).setUserName(
        data['userName'])
    user = user.setPasswordHash(data['passwordHash'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createUser(user)
    resp = {
        "status": status
    }

    return jsonify(resp)


'''
Creates a profile of a contractor
'''


@app.route('/v1.0/person/contractor', methods=['POST'])
def create_contractor_profile():
    data = json.loads(request.get_data())
    contractor = ContractorPOJO.ContractorPOJO()

    # TODO We need to remove some colums from database maybe
    # TODO check how to do multiline code intendentation in python
    # FIXME What is parentId of a contractor?
    # FIXME contractor Id should be
    global userId
    userId = userId + 1
    contractor = contractor.setContractorId(userId).setParentId(data['parentId']).setFirstname(
        data['fname'])
    contractor = contractor.setLastname(data['lname']).setGender(data['gender']).setPhoneNumber(
        data['phno'])
    contractor = contractor.setAddress(data['address']).setAadharStatus(
        data['aadharStatus']).setAadharNo("test")
    contractor = contractor.setPanCard(data['panCard']).setSkill(data['skills']).setActiveInd(
        data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createContractor(contractor)
    resp = {
        "status": status
    }

    return jsonify(resp)


'''
Creates a profile of a laborer
'''


@app.route('/v1.0/person/laborer', methods=['POST'])
def create_laborer_profile():
    user = UserPOJO.UserPOJO()
    data = json.loads(request.get_data())
    laborer = LaborerPOJO.LaborerPOJO()

    # TODO We need to remove some colums from database maybe
    # TODO check how to do multiline code intendentation in python
    global userId
    userId = userId + 1
    user = user.setUserId(userId).setRoleType("l")
    user = user.setPasswordHash(data['passwordHash'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createUser(user)

    laborer = laborer.setLaborerId(userId).setParentId(data['parentId']).setFirstname(data['fname'])
    laborer = laborer.setLastname(data['lname']).setGender(data['gender']).setPhoneNumber(
        data['phno'])
    laborer = laborer.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo(
        "test")
    laborer = laborer.setPanCard(data['panCard']).setSkill(data['skills']).setActiveInd(
        data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createLaborer(laborer)
    resp = {
        "status": status,
        "userId": userId
    }

    return jsonify(resp)


'''
Create a laborer profile for a friend
'''


@app.route('/v1.0/person/laborer/<pid>/laborer', methods=['POST'])
def create_friend_profile(pid):
    data = json.loads(request.get_data())
    laborer = LaborerPOJO.LaborerPOJO()
    laborer = laborer.setLaborerId(data['laborerId']).setParentId(pid).setFirstname(data['fname'])
    laborer = laborer.setLastname(data['lname']).setGender(data['gender']).setPhoneNumber(
        data['phno'])
    laborer = laborer.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo(
        "test")
    laborer = laborer.setPanCard(data['panCard']).setSkill(data['skills']).setActiveInd(
        data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createLaborer(laborer)
    resp = {
        "status": status
    }

    return jsonify(resp)


# TODO PUT may not modify all the values. So, some data[''] may be null. For now lets just try to
# modify active_ind
'''
Modify a profile of a laborer
'''


@app.route('/v1.0/person/laborer/<pid>', methods=['PUT'])
def modify_laborer_profile(pid):
    import pdb
    pdb.set_trace()
    data = json.loads(request.get_data())

    laborer = LaborerPOJO.LaborerPOJO()
    laborer = laborer.setActiveInd(data['activeInd'])
    # laborer = laborer.setLaborerId(pid).setFirstname(data['fname']).setLastname(data['lname']).setPhoneNumber(data['phno'])
    # laborer = laborer.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo("test")
    # laborer = laborer.setPanCard(data['panCard']).setSkill(data['skills']).setActiveInd(data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.updateLaborer(laborer)
    resp = {
        "status": status
    }

    return jsonify(resp)


'''
sample request for modify
{
    "information" :
    {
    "fname": "",
    "lname": "",
    "gender": "",
    "phoneNo": "293842398",
    "address": "",
    "aadharStatus": "",
    "aadharNumber" : "",
    "panCard" : "",
    "skills" : "BUILDING",
    "activeInd" : "",
    "preferred_location": "MAHARASTRA"
    }
}
'''
'''
Modify a profile of a contractor
'''


@app.route('/v1.0/person/contractor/<pid>', methods=['PUT'])
def modify_contractor_profile(pid):
    data = json.loads(request.get_data())

    contractor = ContractorPOJO.ContractorPOJO()
    contractor = contractor.setContractorId(pid).setFirstname(data['fname']).setLastname(
        data['lname']).setPhoneNumber(data['phno'])
    contractor = contractor.setAddress(data['address']).setAadharStatus(
        data['aadharStatus']).setAadharNo("test")
    contractor = contractor.setPanCard(data['panCard']).setSkill(data['skills']).setActiveInd(
        data['activeInd']).setPrefLoc(data['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.updateContractor(contractor)
    resp = {
        "status": status
    }

    return jsonify(resp)


# TODO
@app.route('/v1.0/person/laborer/<pid>/laborer', methods=['GET'])
def get_laborer_and_friends(pid):
    labour = {
        "id": 1,
        "fname": "test_name",
        "Age": 20,
        "phno": 3242324,
        "Gender": "M",
        "preferred_location": "Pune, Mumbai",
        "skills": "Carpenter",
        "Contact No": 9923033442,
        "type": "Self",
        "Status": "Active",
        "links": {
            "self": os.path.dirname(request.path) + '/1',
            "parent": None
        }
    }
    resp = []
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)
    resp.append(labour)

    return jsonify(resp)


# TODO
@app.route('/v1.0/person/session', methods=['POST'])
def get_person_session():
    data = json.loads(request.get_data())
    username = data['username']
    password = data['password']
    resp = {
        "session_id": "1234",
        "role_type": "laborer",
        "id": 0
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
# OPENSSL_CRT_LOC = ''
# OPENSSL_KEY_LOC = ''
# context = ('C:\\Users\\Administrator\\openssl.crt', 'C:\\Users\\Administrator\\openssl.key')
# app.run(debug=False, ssl_context=context)


# Use HTTP. Use this code only for testing!
app.run(debug=True)
