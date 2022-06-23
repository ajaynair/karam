from datetime import datetime

from orm_classes import UserOrmClass, LaborerOrmClass, ContractorOrmClass, JobOrmClass
from PersonTransaction import PersonTransaction


def createTestUser():
    u = UserOrmClass()
    u.username = "abc"
    u.is_laborer = 1
    u.update_time = datetime.now()
    u.password_hash = "abc"

    return u

def createTestLaborer():
    l = LaborerOrmClass()
    l.gender = 1
    l.username = "abc"
    l.address = "addr"
    l.last_name = "def"
    l.first_name = "abc"
    l.aadhar_card_available = True
    l.open_for_work = True
    l.phone_number = "123"
    return l

def createTestContractor():
    c = ContractorOrmClass()
    c.username = "abc"
    c.gender = 1
    c.parent_username = "abc"
    c.address = "addr"
    c.last_name = "def"
    c.phone_number = "def"
    c.first_name = "abc"
    return c

def createTestJob():
    c = JobOrmClass()
    c.id = 1
    c.laborer_username = "abc"
    c.contractor_username = "abc"
    c.is_open = False
    c.last_update_datetime = datetime.now()
    return c

def test_create_laborer():
    p = PersonTransaction()
    p.deleteAllTables()

    u = createTestUser()
    u.setRoleType("x")
    p.createUser(u)

    l = createTestLaborer()
    p.createLaborer(l)

    c = createTestContractor()
    p.createContractor(c)

    u = p.getUserByCred("abc", "abc")
    assert(u.username == "abc")

    u = p.getUser("abc")
    assert(u.username == "abc")

    l.phone_number = "newph"
    p.updateLaborer(l)
    p.updateContractor(c)

    j = createTestJob()
    p.createJob(j)

    # l1 = createTestLaborer()
    # l1.username = "t1"
    # l2 = createTestLaborer()
    # l2.username = "t2"

    # p.createLaborer(l1)
    # p.createLaborer(l2)

    # p.getAllLaborer()

    p.getFriendOfLaborer("abc")

    p.deleteLaborer("abc")
    p.deleteContractor("abc")
    p.deleteJob(1)
    p.deleteUser("abc")
