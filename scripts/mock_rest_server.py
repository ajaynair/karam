#!flask/bin/python
import os

from flask import Flask, jsonify, request
import json

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
    #data = json.loads(request.get_data())

    #skills = data['filter']['skills']
    #locations = data['filter']['locations']

    resp = {
        "id": 1,
        "name": "test_name",
        "age": 20,
        "gender": "M",
        "location": "Pune, Mumbai",
        "skill": "Carpenter",
        "PhoneNo": 9923033442,
        "aadharStatus": 1,
        #            "links": {
        #                "self": request.path + '/1',
        #                "parent": request.path
        #            }
    }
    '''
    resporg = {
        {
            "id": 1,
            "name": "test_name",
            "age": 20,
            "gender": "M",
            "location": "Pune, Mumbai",
            "skill": "Carpenter",
            "PhoneNo": 9923033442,
            "aadharStatus": 1,
#            "links": {
#                "self": request.path + '/1',
#                "parent": request.path
#            }
        },
        {
            "id": 2,
            "Name": "test_name2",
            "Age": 20,
            "Gender": "M",
            "Preferred Location": "Pune, Mumbai",
            "Skill": "Carpenter",
            "Contact No": 9923033442,
            "links": {
                "self": request.path + '/2',
                "parent": request.path
            }
        }
    }
    '''
    return jsonify(resp)

'''
Creates a profile of a contractor
'''
@app.route('/v1.0/person/contractor', methods=['POST'])
def create_contractor_profile():
    data = json.loads(request.get_data())
    print(data)

    #name = data['information']['name']
    #address = data['information']['address']
    #contactno = data['information']['contactno']
    #age = data['information']['age']

    resp = {
        "error": 1
    }

    return jsonify(resp)

'''
Creates a profile of a laborer
'''
@app.route('/v1.0/person/laborer', methods=['POST'])
def create_laborer_profile():
    data = json.loads(request.get_data())
    print ('strat')
    print(data)
    print ('end')

    #name = data['information']['name']
    #address = data['information']['address']
    #contactno = data['information']['contactno']
    #age = data['information']['age']
    #preferred_location = data['information']['preferred_location']
    #aadhar_status = data['information']['aadhar_status']

    resp = {
        "error": 2
    }

    return jsonify(resp)

'''
Modify a profile of a laborer
'''
@app.route('/v1.0/person/laborer/<pid>', methods=['PUT'])
def modify_laborer_profile(pid):
    data = json.loads(request.get_data())
    print(jsonify(data))

    name = data['information']['name']
    address = data['information']['address']
    contactno = data['information']['contactno']
    age = data['information']['age']
    preferred_location = data['information']['preferred_location']
    aadhar_status = data['information']['aadhar_status']
    work_status = data['information']['work_status']

    resp = {
        "error": 0
    }

    return jsonify(resp)

@app.route('/v1.0/person/laborer/<pid>/laborer', methods=['GET'])
def get_laborer_and_friends(pid):
    resp = {
        {
            "id": 1,
            "Name": "test_name",
            "Age": 20,
            "Gender": "M",
            "Preferred Location": "Pune, Mumbai",
            "Skill": "Carpenter",
            "Contact No": 9923033442,
            "type": "Self",
            "Status": "Active",
            "links": {
                "self": os.path.dirname(request.path) + '/1',
                "parent": None
            }
        },
        {
            "id": 2,
            "Name": "test_name2",
            "Age": 20,
            "Gender": "M",
            "Preferred Location": "Pune, Mumbai",
            "Skill": "Carpenter",
            "Contact No": 9923033442,
            "type": "Friend",
            "Status": "Inactive",
            "links": {
                "self": os.path.dirname(request.path) + '/1',
                "parent": None
            }
        }
    }


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


@app.route('/v1.0/person/laborer/<pid>/laborer', methods=['POST'])
def create_friend_profile(pid):
    data = json.loads(request.get_data())
    name = data['information']['name']
    address = data['information']['address']
    contactno = data['information']['contactno']
    age = data['information']['age']
    preferred_location = data['information']['preferred_location']
    aadhar_status = data['information']['aadhar_status']

    resp = {
        "error": 0
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