from typing import List, Tuple

from orm_classes import UserOrmClass, LaborerOrmClass, SkillOrmClass, LocationOrmClass, \
    LaborerPreferredLocationOrmClass, LaborerSkillOrmClass, ContractorOrmClass, JobOrmClass
import sqlalchemy
from sqlalchemy.engine import Engine
import config
from sqlalchemy.exc import SQLAlchemyError
from sqlalchemy.orm import Session

# TODO Give example request body and response body
from rest_dataclasses import LaborerReq, ContractorReq, JobReq, UserReq, SkillReq


def _getNewEngine() -> Engine:
    config.conf_init(path='config_files/config_local.cfg')

    username: str = config.get_mysql_username()
    password: str = config.get_mysql_pwd()
    host: str = config.get_mysql_host()
    dbname: str = config.get_mysql_db()

    mgmt_name: str = 'mysql'
    # Example string: 'mysql://root:abc123@localhost/'
    connect_to_db_str: str = mgmt_name + '://' + username + ':' + password + '@' + host

    # Connect to the database
    # log.debug('Connect to database using string: ', connect_to_db_str)

    engine = sqlalchemy.create_engine(connect_to_db_str)
    try:
        sql_query = 'use ' + dbname
        engine.execute(sql_query)
    except SQLAlchemyError as e:
        reason = 'Failed to select database: ' + dbname, e.__str__()
        # log.info(reason)
        raise Exception(reason)

    return engine


def _getLaborerDcFromOrm(laborerOrm: LaborerReq) -> LaborerReq:
    return LaborerReq(username=laborerOrm.username,
                      parent_username=laborerOrm.parent_username,
                      first_name=laborerOrm.first_name,
                      last_name=laborerOrm.last_name,
                      gender=laborerOrm.gender,
                      phone_number=laborerOrm.phone_number,
                      address=laborerOrm.address,
                      aadhar_card_available=laborerOrm.aadhar_card_available,
                      open_for_work=laborerOrm.open_for_work)


def _getContractorDcFromOrm(contractorOrm: ContractorReq) -> ContractorReq:
    return ContractorReq(username=contractorOrm.username,
                         parent_username=contractorOrm.parent_username,
                         first_name=contractorOrm.first_name,
                         last_name=contractorOrm.last_name,
                         gender=contractorOrm.gender,
                         phone_number=contractorOrm.phone_number,
                         address=contractorOrm.address)


def _getJobDcFromOrm(jobOrm: JobReq) -> JobReq:
    return JobReq(id=jobOrm.id,
                  laborer_username=jobOrm.laborer_username,
                  contractor_username=jobOrm.contractor_username,
                  is_open=jobOrm.is_open,
                  last_update_datetime=jobOrm.last_update_datetime)


def _getUserDcFromOrm(userOrm: UserReq) -> UserReq:
    return UserReq(username=userOrm.username,
                   password_hash=userOrm.password_hash,
                   is_laborer=userOrm.is_laborer,
                   update_time=userOrm.update_time)


def _getLaborerOrmFromDc(laborerDc: LaborerReq) -> LaborerOrmClass:
    return LaborerOrmClass(username=laborerDc.username,
                           parent_username=laborerDc.parent_username,
                           first_name=laborerDc.first_name,
                           last_name=laborerDc.last_name,
                           gender=laborerDc.gender,
                           phone_number=laborerDc.phone_number,
                           address=laborerDc.address,
                           aadhar_card_available=laborerDc.aadhar_card_available,
                           open_for_work=laborerDc.open_for_work)


def _getContractorOrmFromDc(contractorDc: ContractorReq) -> ContractorOrmClass:
    return ContractorOrmClass(username=contractorDc.username,
                         parent_username=contractorDc.parent_username,
                         first_name=contractorDc.first_name,
                         last_name=contractorDc.last_name,
                         gender=contractorDc.gender,
                         phone_number=contractorDc.phone_number,
                         address=contractorDc.address)


def _getJobOrmFromDc(jobDc: JobReq) -> JobOrmClass:
    return JobOrmClass(id=jobDc.id,
                       laborer_username=jobDc.laborer_username,
                       contractor_username=jobDc.contractor_username,
                       is_open=jobDc.is_open,
                       last_updated_datetime=jobDc.last_update_datetime)


def _getUserOrmFromDc(userDc: UserReq) -> UserOrmClass:
    return UserOrmClass(username=userDc.username,
                        password_hash=userDc.password_hash,
                        is_laborer=userDc.is_laborer,
                        update_time=userDc.update_time)


def _getSkillOrmFromDc(skillDc: SkillReq) -> SkillOrmClass:
    return SkillOrmClass(skillname=skillDc.skillname)


def createSkill(skillDc: SkillReq) -> None:
    engine = _getNewEngine()
    skillOrm = _getSkillOrmFromDc(skillDc)
    import pdb
    pdb.set_trace()
    with Session(engine, expire_on_commit=False) as session:
        session.add(skillOrm)
        session.commit()


def createLaborer(laborerDc: LaborerReq) -> None:
    engine = _getNewEngine()

    laborerOrm = _getLaborerOrmFromDc(laborerDc)
    with Session(engine, expire_on_commit=False) as session:
        session.add(laborerOrm)
        session.commit()


def updateLaborer(username: str, laborerDc: LaborerReq) -> None:
    engine = _getNewEngine()

    laborerOrm = _getLaborerOrmFromDc(laborerDc)
    with Session(engine, expire_on_commit=False) as session:
        laborerObj = session.query(LaborerOrmClass).filter(laborerOrm.username == username)
        newLaborerValues = {
            LaborerOrmClass.parent_username: laborerOrm.parent_username,
            LaborerOrmClass.last_name: laborerOrm.last_name,
            LaborerOrmClass.gender: laborerOrm.gender,
            LaborerOrmClass.first_name: laborerOrm.first_name,
            LaborerOrmClass.phone_number: laborerOrm.phone_number,
            LaborerOrmClass.address: laborerOrm.address,
            LaborerOrmClass.open_for_work: laborerOrm.open_for_work,
            LaborerOrmClass.aadhar_card_available: laborerOrm.aadhar_card_available
        }

        laborerObj.update(newLaborerValues)
        session.commit()


def getAllLaborer(skill, location) -> List[LaborerReq]:
    engine = _getNewEngine()
    import pdb
    pdb.set_trace()
    with Session(engine, expire_on_commit=False) as session:
        skillObj = session.query(SkillOrmClass).filter(LaborerSkillOrmClass == skill).one_or_none()
        skillObj = session.query(SkillOrmClass).filter(LaborerSkillOrmClass.skillname == skill).one_or_none()
        locationObj = session.query(LocationOrmClass).filter(LocationOrmClass.name == location)
        laborerUsernames1 = session.query(LaborerPreferredLocationOrmClass).filter(
            LaborerPreferredLocationOrmClass.skill_id == skillObj.id)
        laborerUsernames2 = session.query(LaborerSkillOrmClass).filter(LaborerSkillOrmClass.username == locationObj.id)
        laborerOrms = sqlalchemy.intersect(session.get(UserOrmClass, laborerUsernames1),
                                           session.get(UserOrmClass, laborerUsernames2))

        laborerDcs = []
        for laborerOrm in laborerOrms:
            laborerDcs.append(_getLaborerDcFromOrm(laborerOrm))
        return laborerDcs


def getFriendOfLaborer(parentUsername) -> LaborerReq:
    engine = _getNewEngine()

    with Session(engine, expire_on_commit=False) as session:
        parentOrm = session.query(LaborerOrmClass).filter(
            sqlalchemy.or_(LaborerOrmClass.username == parentUsername,
                           LaborerOrmClass.parent_username == parentUsername))

    return _getLaborerDcFromOrm(parentOrm)


def createContractor(contractorDc: ContractorReq) -> None:
    engine = _getNewEngine()
    contractorOrm = _getContractorOrmFromDc(contractorDc)

    with Session(engine, expire_on_commit=False) as session:
        session.add(contractorOrm)
        session.commit()


def updateContractor(contractorDc: ContractorReq) -> None:
    engine = _getNewEngine()
    contractorOrm = _getContractorOrmFromDc(contractorDc)

    with Session(engine, expire_on_commit=False) as session:
        contractorObj = session.query(ContractorOrmClass).filter(ContractorOrmClass.username == contractorOrm.username)
        updatedContractor = {
            ContractorOrmClass.gender: contractorOrm.gender,
            ContractorOrmClass.address: contractorOrm.address,
            ContractorOrmClass.first_name: contractorOrm.first_name,
            ContractorOrmClass.last_name: contractorOrm.last_name,
            ContractorOrmClass.phone_number: contractorOrm.phone_number,
            ContractorOrmClass.parent_username: contractorOrm.parent_username,
        }

        contractorObj.update(updatedContractor)
        session.commit()


def getAllContractor() -> List[ContractorReq]:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        contractorOrms = session.query(ContractorOrmClass).all()
        # contractorOrms = ContractorOrmClass.query.all()
        contractorDcs = []
        for contractorOrm in contractorOrms:
            contractorDcs.append(_getContractorDcFromOrm(contractorOrm))
        return contractorDcs


def createJob(jobDc: JobReq) -> None:
    engine = _getNewEngine()
    jobOrm = _getJobOrmFromDc(jobDc)

    with Session(engine, expire_on_commit=False) as session:
        session.add(jobOrm)
        session.commit()


def createUser(userDc: UserReq) -> None:
    engine = _getNewEngine()
    userOrm = _getUserOrmFromDc(userDc)

    with Session(engine, expire_on_commit=False) as session:
        session.add(userOrm)
        session.commit()


def deleteLaborer(username: str) -> None:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        laborer = session.get(LaborerOrmClass, username)
        session.delete(laborer)
        session.commit()


def deleteJob(jid: int) -> None:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        job = session.get(JobOrmClass, jid)
        session.delete(job)
        session.commit()


def deleteContractor(username: str) -> None:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        contractor = session.get(ContractorOrmClass, username)
        session.delete(contractor)
        session.commit()


def deleteUser(username: str) -> None:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        user = session.get(UserOrmClass, username)
        session.delete(user)
        session.commit()


def getUserByCred(username: str, password: str) -> UserReq:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        potentialUser: UserOrmClass = session.get(UserOrmClass, username)
        if potentialUser.password_hash != password:
            raise ValueError('Incorrect password')

        return _getUserDcFromOrm(potentialUser)


def getUser(username: str) -> UserReq:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        userOrm = session.get(UserOrmClass, username)
    return _getUserDcFromOrm(userOrm)


def deleteAllTables() -> None:
    engine = _getNewEngine()
    with Session(engine, expire_on_commit=False) as session:
        session.query(LaborerOrmClass).delete()
        session.query(ContractorOrmClass).delete()
        session.query(JobOrmClass).delete()
        session.query(UserOrmClass).delete()
        session.query(LaborerPreferredLocationOrmClass).delete()
        session.query(LaborerSkillOrmClass).delete()
        session.query(SkillOrmClass).delete()
        session.query(LocationOrmClass).delete()
        session.commit()
