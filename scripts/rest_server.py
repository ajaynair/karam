#!flask/bin/python
import os

from flask import Flask, jsonify, request
import json
import PersonTransaction,LaborerPOJO,ContractorPOJO,UserPOJO,JobPOJO
import datetime

app = Flask(__name__)

# TODO Validate JSON schema

@app.route('/v1.0/test/withbody', methods=['POST'])
def get_test2():
    print (json.loads(request.get_data()))
    resp = {"test":"pass"}
    return jsonify(resp)

@app.route('/v1.0/test', methods=['GET'])
def get_test():
    resp = {"test":"pass"}
    return jsonify(resp)

@app.route('/v1.0/test', methods=['POST'])
def post_test():
    resp = {"test":"pass"}
    return jsonify(resp)

@app.route('/v1.0/test/<id>/test/<pid>', methods=['POST'])
def post_test_id(id, pid):
    pid = str(pid)
    resp = {"test":pid}
    return jsonify(resp)

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
    data = json.loads(request.get_data())
    skills = data['filter']['skills']
    locations = data['filter']['locations']
    obj1 = PersonTransaction.PersonTransaction()
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

    #TODO We need to remove some colums from database maybe
    #TODO check how to do multiline code intendentation in python
    job = job.setLaborerId(data['information']['laborerId']).setContractorId(data['information']['contractorId'])
    job = job.setActiveInd(data['information']['activeInd'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createJob(job)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
Creates a profile of a user while signup
'''
@app.route('/v1.0/person/signup', methods=['POST'])
def create_user_profile():
    data = json.loads(request.get_data())
    user = UserPOJO.UserPOJO()

    #TODO We need to remove some colums from database maybe
    #TODO check how to do multiline code intendentation in python
    user = user.setRoleType(data['information']['roleType']).setUserName(data['information']['userName'])
    user = user.setPasswordHash(data['information']['passwordHash'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createUser(user)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
Creates a profile of a contractor
'''
@app.route('/v1.0/person/contractor', methods=['POST'])
def create_contractor_profile():
    data = json.loads(request.get_data())
    contractor = ContractorPOJO.ContractorPOJO()

    #TODO We need to remove some colums from database maybe
    #TODO check how to do multiline code intendentation in python
    contractor = contractor.setContractorId(data['information']['contractorId']).setParentId(data['information']['parentId']).setFirstname(data['information']['fname'])
    contractor = contractor.setLastname(data['information']['lname']).setGender(data['information']['gender']).setPhoneNumber(data['information']['phno'])
    contractor = contractor.setAddress(data['information']['address']).setAadharStatus(data['information']['aadharStatus']).setAadharNo(data['information']['aadharNumber'])
    contractor = contractor.setPanCard(data['information']['panCard']).setSkill(data['information']['skill']).setActiveInd(data['information']['activeInd']).setPrefLoc(data['information']['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createContractor(contractor)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
Creates a profile of a laborer
'''
@app.route('/v1.0/person/laborer', methods=['POST'])
def create_laborer_profile():
    data = json.loads(request.get_data())
    laborer = LaborerPOJO.LaborerPOJO()

    #TODO We need to remove some colums from database maybe
    #TODO check how to do multiline code intendentation in python
    laborer = laborer.setLaborerId(data['information']['laborerId']).setParentId(data['information']['parentId']).setFirstname(data['information']['fname'])
    laborer = laborer.setLastname(data['information']['lname']).setGender(data['information']['gender']).setPhoneNumber(data['information']['phno'])
    laborer = laborer.setAddress(data['information']['address']).setAadharStatus(data['information']['aadharStatus']).setAadharNo(data['information']['aadharNumber'])
    laborer = laborer.setPanCard(data['information']['panCard']).setSkill(data['information']['skill']).setActiveInd(data['information']['activeInd']).setPrefLoc(data['information']['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createLaborer(laborer)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
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
    laborer = laborer.setLaborerId(friendUserID).setParentId(pid).setFirstname(data['information']['fname'])
    laborer = laborer.setLastname(data['information']['lname']).setGender(data['information']['gender']).setPhoneNumber(data['information']['phno'])
    laborer = laborer.setAddress(data['information']['address']).setAadharStatus(data['information']['aadharStatus']).setAadharNo(data['information']['aadharNumber'])
    laborer = laborer.setPanCard(data['information']['panCard']).setSkill(data['information']['skill']).setActiveInd(data['information']['activeInd']).setPrefLoc(data['information']['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.createLaborer(laborer)
    resp = {
        "status" : status
    }

    return jsonify(resp)

'''
Modify profile of a laborer
'''
@app.route('/v1.0/person/laborer/<pid>', methods=['PUT'])
def modify_laborer_profile(pid):
    data = json.loads(request.get_data())

    laborer = LaborerPOJO.LaborerPOJO()
    laborer = laborer.setLaborerId(pid).setFirstname(data['information']['fname']).setLastname(data['information']['lname']).setPhoneNumber(data['information']['phno'])
    laborer = laborer.setAddress(data['information']['address']).setAadharStatus(data['information']['aadharStatus']).setAadharNo(data['information']['aadharNumber'])
    laborer = laborer.setPanCard(data['information']['panCard']).setSkill(data['information']['skill']).setActiveInd(data['information']['activeInd']).setPrefLoc(data['information']['preferred_location'])

    obj1 = PersonTransaction.PersonTransaction()
    status = obj1.updateLaborer(laborer)
    resp = {
        "status" : status
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
'''
'''
Modify a profile of a contractor
'''
@app.route('/v1.0/person/contractor/<pid>', methods=['PUT'])
def modify_contractor_profile(pid):
    data = json.loads(request.get_data())

    contractor = ContractorPOJO.ContractorPOJO()
    contractor = contractor.setContractorId(pid).setFirstname(data['information']['fname']).setLastname(data['information']['lname']).setPhoneNumber(data['information']['phno'])
    contractor = contractor.setAddress(data['information']['address']).setAadharStatus(data['information']['aadharStatus']).setAadharNo(data['information']['aadharNumber'])
    contractor = contractor.setPanCard(data['information']['panCard']).setSkill(data['information']['skill']).setActiveInd(data['information']['activeInd']).setPrefLoc(data['information']['preferred_location'])

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
    return jsonify(obj1.getFriendOfLaborer(pid))


@app.route('/v1.0/person/session', methods=['POST'])
def get_person_session():
    data = json.loads(request.get_data())
    username = data['username']
    password = data['password']
    resp = {
        "sessionid": "1234",
        "id": 1
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
