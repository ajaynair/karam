import getopt
import sys
from sys import argv

import sqlalchemy
from config import config

from backend.SQLAlchemy.table_classes import User, \
    Contractor, \
    Laborer, \
    Skill, \
    Location, \
    LaborerPreferredLocation, \
    LaborerSkill


def delete_database(engine, dbname: str) -> None:
    sql_query: str = "drop database " + dbname
    engine.execute(sql_query)


def setup_database(engine, dbname: str) -> None:
    sql_query: str = "create database " + dbname
    engine.execute(sql_query)

    User.__table__.create(engine)
    Contractor.__table__.create(engine)
    Laborer.__table__.create(engine)
    Skill.__table__.create(engine)
    Location.__table__.create(engine)
    LaborerPreferredLocation.__table__.create(engine)
    LaborerSkill.__table__.create(engine)


def print_help():
    print('db_setup.py -h : Prints this help message')
    print('db_setup.py -d : Delete database')
    print('db_setup.py -s : Setup new database')


def perform_actions(engine, dbname, user_args):
    try:
        opts, args = getopt.getopt(user_args, "hds", None)
    except getopt.GetoptError:
        print_help()
        sys.exit(2)

    for opt, _ in opts:
        if opt == '-h':
            print_help()
            sys.exit()

        elif opt == 'd':
            delete_database(engine, dbname)

        elif opt == 's':
            setup_database(engine, dbname)


def main(user_args):
    engine = sqlalchemy.create_engine('mysql://root:abc123@localhost/karamdb')
    engine.execute("use karamdb")
    perform_actions(engine, "karamdb", user_args)


if __name__ == "__main__":
    main(argv[1:])
