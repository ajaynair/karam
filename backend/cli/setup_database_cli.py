import getopt
import sys
from sys import argv

import sqlalchemy
import logging

from sqlalchemy.engine import Engine
from sqlalchemy.exc import SQLAlchemyError

from db_orm.error import DbError

log: logging.Logger = logging.getLogger(__file__)
engine: Engine

from db_orm.table_classes import User, \
    Contractor, \
    Laborer, \
    Skill, \
    Location, \
    LaborerPreferredLocation, \
    LaborerSkill
from config import config


def delete_database(dbname: str) -> None:
    log.debug('Deleting tables: ' + dbname)
    try:
        sql_query: str = "drop database " + dbname
        engine.execute(sql_query)
    except SQLAlchemyError as e:
        reason = 'Failed to delete database: ' + dbname, e.__str__()
        log.info(reason)
        raise DbError(reason)


def create_database(dbname: str) -> None:
    log.debug('Creating database: ' + dbname)
    try:
        sql_query: str = "create database " + dbname
        engine.execute(sql_query)
    except SQLAlchemyError as e:
        reason = 'Failed to create database: ' + dbname, e.__str__()
        log.info(reason)
        raise DbError(reason)


def select_database(dbname: str):
    log.debug('Selecting database: ' + dbname)
    try:
        sql_query = 'use ' + dbname
        engine.execute(sql_query)
    except SQLAlchemyError as e:
        reason = 'Failed to select database: ' + dbname, e.__str__()
        log.info(reason)
        raise DbError(reason)


def create_tables():
    log.debug('Creating tables')
    try:
        User.__table__.create(engine)
        Contractor.__table__.create(engine)
        Laborer.__table__.create(engine)
        Skill.__table__.create(engine)
        Location.__table__.create(engine)
        LaborerPreferredLocation.__table__.create(engine)
        LaborerSkill.__table__.create(engine)
    except SQLAlchemyError as e:
        reason = 'Failed to create tables', e.__str__()
        log.info(reason)
        raise DbError(reason)


def setup_database(dbname: str) -> None:
    log.debug('Setting up database: ' + dbname)
    try:
        delete_database(dbname)
    except DbError as e:
        log.debug('Silently continuing as database is not required to exist')

    create_database(dbname)

    select_database(dbname)

    create_tables()


def print_help() -> None:
    print('db_setup.py -h : Prints this help message')
    print('db_setup.py -s : Setup new database')


def perform_actions(dbname: str, user_args) -> None:
    try:
        opts, args = getopt.getopt(user_args, "hs", [])
        if not opts:
            raise getopt.GetoptError

    except getopt.GetoptError:
        print_help()
        sys.exit(2)

    for opt, _ in opts:
        if opt == '-h':
            print_help()
            sys.exit()

        elif opt == '-s':
            setup_database(dbname)


def connect_to_db_mgmt(username: str, password: str, host: str) -> None:
    mgmt_name: str = 'mysql'
    # Example string: 'mysql://root:abc123@localhost/'
    connect_to_db_str: str = mgmt_name + '://' + username + ':' + password + '@' + host

    # Connect to the database
    log.debug('Connect to database using string: ', connect_to_db_str)

    global engine
    engine = sqlalchemy.create_engine(connect_to_db_str)


def main(user_args) -> None:
    # Get database information from config

    config.conf_init()

    username: str = config.get_mysql_username()
    password: str = config.get_mysql_pwd()
    host: str = config.get_mysql_host()
    dbname: str = config.get_mysql_db()

    # Connect to the database
    connect_to_db_mgmt(username, password, host)

    # Perform actions as requested by the user
    perform_actions(dbname, user_args)


if __name__ == "__main__":
    try:
        main(argv[1:])
    except Exception as e:
        reason = 'Some error occurred: ' + e.__str__()
        print(reason)
