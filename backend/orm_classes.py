from typing import Any

from sqlalchemy import Column, Integer, String, ForeignKey, Boolean, DateTime, PrimaryKeyConstraint # type: ignore
from sqlalchemy.ext.declarative import declarative_base  # type: ignore

SHORT_STRING_LEN = 200
LONG_STRING_LEN = 2000

Base: Any = declarative_base()


class UserOrmClass(Base):
    __tablename__: str = 'user'

    username: Column = Column(String(SHORT_STRING_LEN), primary_key=True)
    password_hash: Column = Column(String(SHORT_STRING_LEN))
    is_laborer: Column = Column(Boolean)
    update_time: Column = Column(DateTime)


class LaborerOrmClass(Base):
    __tablename__: str = 'laborer'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(UserOrmClass.username), primary_key=True)
    parent_username: Column = Column(String(SHORT_STRING_LEN))
    first_name: Column = Column(String(SHORT_STRING_LEN))
    last_name: Column = Column(String(SHORT_STRING_LEN))
    gender: Column = Column(Integer)
    phone_number: Column = Column(String(SHORT_STRING_LEN))
    address: Column = Column(String(LONG_STRING_LEN))
    aadhar_card_available: Column = Column(Boolean)
    open_for_work: Column = Column(Boolean)


class ContractorOrmClass(Base):
    __tablename__: str = 'contractor'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(UserOrmClass.username), primary_key=True)
    parent_username: Column = Column(String(SHORT_STRING_LEN))
    first_name: Column = Column(String(SHORT_STRING_LEN))
    last_name: Column = Column(String(SHORT_STRING_LEN))
    gender: Column = Column(Integer)
    phone_number: Column = Column(String(SHORT_STRING_LEN))
    address: Column = Column(String(LONG_STRING_LEN))

# TODO Expand to include city, state etc.
class LocationOrmClass(Base):
    __tablename__: str = 'location'

    locationname: Column = Column(String(SHORT_STRING_LEN), primary_key=True)


class SkillOrmClass(Base):
    __tablename__: str = 'skill'

    skillname: Column = Column(String(SHORT_STRING_LEN), primary_key=True)


class JobOrmClass(Base):
    __tablename__: str = "job"
    id: Column = Column(Integer, primary_key=True)
    laborer_username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(UserOrmClass.username))
    contractor_username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(UserOrmClass.username))
    is_open: Column = Column(Boolean)
    last_update_datetime: Column = Column(DateTime)


class LaborerSkillOrmClass(Base):
    __tablename__: str = 'laborer_skill'

    id: Column = Column(Integer, primary_key=True)
    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(LaborerOrmClass.username))
    skillname: Column = Column(String(SHORT_STRING_LEN), ForeignKey(SkillOrmClass.skillname))


class LaborerPreferredLocationOrmClass(Base):
    __tablename__: str = 'laborer_preferred_locations'

    username: Column = Column(String(SHORT_STRING_LEN), ForeignKey(UserOrmClass.username))
    locationname: Column = Column(String(SHORT_STRING_LEN), ForeignKey(LocationOrmClass.locationname))
    __table_args__: tuple = (
        PrimaryKeyConstraint('username', 'locationname'),
        {},
    )
