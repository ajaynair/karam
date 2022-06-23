from dataclasses import dataclass, field
from datetime import datetime
import marshmallow.validate
import marshmallow_dataclass


@dataclass
class SkillReq:
    skillname: str


@dataclass
class JobReq:
    id: int = field(metadata={"validate": marshmallow.validate.Range(min=0)})
    laborer_username: str
    contractor_username: str = field(default=None)
    is_open: bool = field(default=True)
    last_update_datetime: datetime = field(default=datetime.now())


@dataclass
class UserReq:
    username: str
    is_laborer: bool
    password_hash: str = field(default=None)
    update_time: datetime = field(default=datetime.now())


@dataclass
class ContractorReq:
    username: str
    parent_username: str
    first_name: str
    last_name: str
    gender: int
    phone_number: str
    address: str


@dataclass
class LaborerReq:
    username: str
    parent_username: str
    first_name: str
    last_name: str
    gender: int
    phone_number: str
    address: str
    aadhar_card_available: bool
    open_for_work: bool


if __name__ == "__main__":
    jobJson = '{"id": 1, "laborer_username": "abc", "contractor_username": "abc"}'

    schema = marshmallow_dataclass.class_schema(JobReq)()
    jobObj = schema.loads(jobJson)
    print(jobObj)
