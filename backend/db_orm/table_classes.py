from typing import Any, Union

from sqlalchemy import Column, Integer, String, ForeignKey, Boolean, DateTime, PrimaryKeyConstraint # type: ignore
from sqlalchemy.ext.declarative import declarative_base  # type: ignore

SHORT_STRING_LEN = 200
LONG_STRING_LEN = 2000

Base: Any = declarative_base()


class User(Base):
    __tablename__ = 'user'

    username: Column = Column(String(SHORT_STRING_LEN), primary_key=True)
    password_hash: Column = Column(String(SHORT_STRING_LEN))
    role_type: Column = Column(Boolean)
    update_time: Column = Column(DateTime)


class Laborer(Base):
    __tablename__ = 'laborers'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(User.username), primary_key=True)
    parent_username: Column = Column(String(SHORT_STRING_LEN))
    first_name: Column = Column(String(SHORT_STRING_LEN))
    last_name: Column = Column(String(SHORT_STRING_LEN))
    gender: Column = Column(Integer)
    phone_number: Column = Column(String(SHORT_STRING_LEN))
    address: Column = Column(String(LONG_STRING_LEN))
    aadhar_card_status: Column = Column(Boolean)
    open_for_work: Column = Column(Boolean)

class Contractor(Base):
    __tablename__ = 'contractors'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(User.username), primary_key=True)
    parent_username: Column = Column(String(SHORT_STRING_LEN))
    first_name: Column = Column(String(SHORT_STRING_LEN))
    last_name: Column = Column(String(SHORT_STRING_LEN))
    gender: Column = Column(Integer)
    phone_number: Column = Column(String(SHORT_STRING_LEN))
    address: Column = Column(String(LONG_STRING_LEN))


class Skill(Base):
    __tablename__ = 'skills'

    id: Column = Column(Integer, primary_key=True)
    name: Column = Column(String(SHORT_STRING_LEN))


class Location(Base):
    __tablename__ = 'locations'

    id: Column = Column(Integer, primary_key=True)
    name: Column = Column(String(SHORT_STRING_LEN))


class LaborerPreferredLocation(Base):
    __tablename__ = 'laborer_preferred_locations'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(User.username))
    skill_id: Column = Column(Integer, ForeignKey(Skill.id))
    __table_args__: tuple = (
        PrimaryKeyConstraint('username', 'skill_id'),
        {},
    )


class LaborerSkill(Base):
    __tablename__ = 'laborer_skills'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(User.username))
    location_id: Column = Column(Integer, ForeignKey(Location.id))

    __table_args__: tuple = (
        PrimaryKeyConstraint('username', 'location_id'),
        {},
    )
