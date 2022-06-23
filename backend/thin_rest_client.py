import requests


# requests.post("http://127.0.0.1:5000/v1.0/skill", json={'skillname': 'carpenter'})
# requests.post("http://127.0.0.1:5000/v1.0/user/signup", json={'username': 'usr2',
#                                                               'is_laborer': False,
#                                                               'password_hash': 'pwd'
#                                                               })
# requests.post("http://127.0.0.1:5000/v1.0/user/signup", json={'username': 'usr1',
#                                                               'is_laborer': False,
#                                                               'password_hash': 'pwd'
#                                                               })
# requests.post("http://127.0.0.1:5000/v1.0/user/laborer", json={'username': 'usr1',
#                                                                'parent_username': 'usr1',
#                                                                'first_name': 'fname',
#                                                                'last_name': 'lname',
#                                                                'gender': 1,
#                                                                'phone_number': '1234',
#                                                                'address': 'addr',
#                                                                'aadhar_card_available': True,
#                                                                'open_for_work': True})
# requests.post("http://127.0.0.1:5000/v1.0/user/contractor", json={'username': 'usr1',
#                                                                'parent_username': 'usr1',
#                                                                'first_name': 'fname',
#                                                                'last_name': 'lname',
#                                                                'gender': 1,
#                                                                'phone_number': '1234',
#                                                                'address': 'addr'})

# requests.post("http://127.0.0.1:5000/v1.0/job", json={'laborer_username': 'usr1'})

response = requests.get("http://127.0.0.1:5000/v1.0/user/contractor")
print(response.text)
# print(response.status_code)
#
response = requests.get("http://127.0.0.1:5000/v1.0/user/laborer?skill=carpenter&location=someloc")
print(response.text)
# print(response.status_code)
