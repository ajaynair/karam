import marshmallow_dataclass
from flask import Flask, jsonify, request
import json
from rest_dataclasses import LaborerReq, ContractorReq, UserReq, JobReq, SkillReq
import db_transactions

app = Flask(__name__)


@app.route('/v1.0/skill', methods=['POST'])
def create_skill():
    schema = marshmallow_dataclass.class_schema(SkillReq)()

    skill = schema.loads(request.get_data())
    status = db_transactions.createSkill(skill)
    resp = {
        "status": status
    }

    return jsonify(resp)


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


@app.route('/v1.0/user/laborer', methods=['GET'])
def get_laborer_list():
    skill = request.args.get('skill')
    location = request.args.get('location')

    laborers = db_transactions.getAllLaborer(skill, location)
    schema = marshmallow_dataclass.class_schema(LaborerReq)()

    return schema.dumps(laborers, many=True)


'''
{

}
Returns a list of contractors
'''


@app.route('/v1.0/user/contractor', methods=['GET'])
def get_contractor_list():
    contractors = db_transactions.getAllContractor()
    schema = marshmallow_dataclass.class_schema(ContractorReq)()
    return schema.dumps(contractors, many=True)


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


# @app.route('/v1.0/job', methods=['POST'])
# def create_job():
#     schema = marshmallow_dataclass.class_schema(JobReq)()
#     job = schema.loads(request.get_data())
#
#     status = db_transactions.createJob(job)
#     resp = {
#         "status": status
#     }
#
#     return jsonify(resp)


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


@app.route('/v1.0/user/signup', methods=['POST'])
def create_user_profile():
    schema = marshmallow_dataclass.class_schema(UserReq)()
    user = schema.loads(request.get_data())

    status = db_transactions.createUser(user)
    resp = {
        "status": status
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


@app.route('/v1.0/user/contractor', methods=['POST'])
def create_contractor_profile():
    schema = marshmallow_dataclass.class_schema(ContractorReq)()
    contractor = schema.loads(request.get_data())

    # user = UserReq(username=contractor.username, is_laborer=False)
    # db_transactions.createUser(user)
    status = db_transactions.createContractor(contractor)

    resp = {
        "status": status
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


@app.route('/v1.0/user/laborer', methods=['POST'])
def create_laborer_profile():
    schema = marshmallow_dataclass.class_schema(LaborerReq)()
    laborer = schema.loads(request.get_data())

    # user = UserReq(username=laborer.username, is_laborer=True)
    # db_transactions.createUser(user)
    status = db_transactions.createLaborer(laborer)

    resp = {
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
Create a laborer profile for a parent
'''


# @app.route('/v1.0/user/laborer/<pusername>/laborer', methods=['POST'])
# def create_parent_profile(pusername):
#     schema = marshmallow_dataclass.class_schema(LaborerReq)()
#     laborer = schema.loads(request.get_data())
#
#     user = UserReq(username=laborer.username, is_laborer=True)
#     db_transactions.createUser(user)
#     status = db_transactions.createLaborer(laborer)
#
#     resp = {
#         "status": status
#     }
#
#     return jsonify(resp)


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

# @app.route('/v1.0/user/laborer/<username>', methods=['PUT'])
# def modify_laborer_profile(username):
#     data = json.loads(request.get_data())
#
#     laborer = LaborerReq.Laborer()
#     laborer = laborer.setActiveInd(data['active_ind'])
#
#     status = db_transactions.updateLaborer(username, laborer)
#     resp = {
#         "status": status
#     }
#
#     return jsonify(resp)
#

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

# @app.route('/v1.0/user/contractor/<pid>', methods=['PUT'])
# def modify_contractor_profile(pid):
#     data = json.loads(request.get_data())
#
#     contractor = ContractorReq.Contractor()
#     contractor = contractor.setContractorId(pid).setFirstname(data['fname']).setLastname(data['lname']).setPhoneNumber(
#         data['phno'])
#     contractor = contractor.setAddress(data['address']).setAadharStatus(data['aadharStatus']).setAadharNo(
#         data['aadharNumber'])
#     contractor = contractor.setPanCard(data['panCard']).setSkill(data['skill']).setActiveInd(
#         data['activeInd']).setPrefLoc(data['preferred_location'])
#
#     status = db_transactions.updateContractor(contractor)
#     resp = {
#         "status": status
#     }
#
#     return jsonify(resp)
#

'''
Return list of laborer who are created/parent by/of pid laborer
'''


# @app.route('/v1.0/user/laborer/<pid>/laborer', methods=['GET'])
# def get_laborer_and_parents(pid):
#     db_transactions.getFriendOfLaborer(pid)
#     return jsonify(db_transactions.getFriendOfLaborer(pid))


@app.route('/v1.0/user/session', methods=['POST'])
def get_user_session():
    data = json.loads(request.get_data())
    username = data['username']
    password = data['password']
    id, is_laborer = db_transactions.getUserByCred(username, password)
    resp = {
        "id": id,
        "is_laborer": is_laborer
    }
    return jsonify(resp)


##################################################################
# APIs for the future ignore for now
##################################################################
@app.route('/v1.0/user', methods=['DELETE'])
def delete_user():
    data = json.loads(request.get_data())
    pid = data['pid']
    resp = {
        "error": 0
    }

    return jsonify(resp)


@app.route('/v1.0/user/<pid>/job', methods=['DELETE'])
def delete_user_job(pid):
    return "pid"


@app.route('/v1.0/user/<pid>/job/<jid>', methods=['GET'])
def get_user_job_id(pid, jid):
    return "pid"


@app.route('/v1.0/user/<pid>/job/<jid>', methods=['PUT'])
def put_user_job(pid, jid):
    return "pid"


@app.route('/v1.0/user/<pid>/activity_log', methods=['POST'])
def post_user_activity_log(pid):
    return "pid"


@app.route('/v1.0/user/<pid>/setting', methods=['GET'])
def get_user_setting(pid):
    return "pid"


@app.route('/v1.0/user/<pid>/setting', methods=['PUT'])
def put_user_settings(pid):
    return "pid2"


@app.route('/v1.0/user/<pid>/about_me', methods=['GET'])
def get_about_me(pid):
    return "pid"


# /user/<id>
@app.route('/v1.0/user/<pid>', methods=['GET'])
def get_user_details(pid):
    return "pid"


@app.route('/v1.0/user/<pid>', methods=['PUT'])
def put_user_details(pid):
    return "pid"


@app.route('/v1.0/user/<pid>/credential', methods=['PUT'])
def put_user_credential(pid):
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
