CREATE TABLE person (
        person_id varchar(50),
         first_name varchar(100),
         last_name varchar(100),
         gender varchar(10),
         phone_number varchar(15),
         address varchar(500),
         adhar_card_number varchar(50),
         adhar_card_status varchar(5),
         pan_card varchar(20),
         CONSTRAINT person_id_pkey PRIMARY KEY (person_id));
         
CREATE TABLE credentials (
         user_name varchar(100) NOT NULL PRIMARY KEY,
         person_id varchar(50),
         password_hash varchar(100),
         update_date_time DATETIME DEFAULT CURRENT_TIMESTAMP );
         
ALTER TABLE credentials ADD FOREIGN KEY (person_id) REFERENCES person(person_id);


CREATE TABLE personnel (
         prsnl_id varchar(50) NOT NULL PRIMARY KEY,
         person_id varchar(50),
         role_type varchar(10),
         skill varchar(200),
         active_ind varchar(2),
         preferred_job_location varchar(200));
         
ALTER TABLE personnel
ADD FOREIGN KEY (person_id) REFERENCES person(person_id);

CREATE TABLE job (
         job_id varchar(50) NOT NULL PRIMARY KEY,
         labour_id varchar(50),
         contractor_id varchar(50),
         active_ind varchar(2),
         update_date_time DATETIME DEFAULT current_timestamp);
         
         
ALTER TABLE job
ADD FOREIGN KEY (labour_id) REFERENCES person(person_id);


create table user_activity (
        user_activity_id varchar(50) NOT NULL PRIMARY KEY,
        role_type varchar(10),
        activity_log varchar(200),
        update_date_time DATETIME DEFAULT current_timestamp
);

ALTER TABLE job
ADD FOREIGN KEY (contractor_id) REFERENCES person(person_id);

commit;