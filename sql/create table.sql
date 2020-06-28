CREATE TABLE user (
		 user_id varchar(50) NOT NULL PRIMARY KEY,
         role_type varchar(2) NOT NULL,
         user_name varchar(100) NOT NULL,
         password_hash varchar(100) NOT NULL,
         update_date_time DATETIME DEFAULT CURRENT_TIMESTAMP );
         
CREATE TABLE laborer (
         laborer_id varchar(50) NOT NULL PRIMARY KEY,
         user_id varchar(50) NOT NULL,
         first_name varchar(100),
         last_name varchar(100),
         gender varchar(10),
         phone_number varchar(15),
         address varchar(500),
         adhar_card_number varchar(50),
         adhar_card_status varchar(5),
         pan_card varchar(20),
         skill varchar(200),
         active_ind varchar(2),
         preferred_job_location varchar(200));

ALTER TABLE laborer
ADD FOREIGN KEY (user_id) REFERENCES user(user_id);

CREATE TABLE contractor (
         contractor_id varchar(50) NOT NULL PRIMARY KEY,
         user_id varchar(50) NOT NULL,
         first_name varchar(100),
         last_name varchar(100),
         gender varchar(10),
         phone_number varchar(15),
         address varchar(500),
         adhar_card_number varchar(50),
         adhar_card_status varchar(5),
         pan_card varchar(20),
         skill varchar(200),
         active_ind varchar(2),
         preferred_job_location varchar(200));

ALTER TABLE contractor
ADD FOREIGN KEY (user_id) REFERENCES user(user_id);
         
CREATE TABLE job (
         job_id varchar(50) NOT NULL PRIMARY KEY,
         labour_id varchar(50),
         contractor_id varchar(50),
         active_ind varchar(2),
         update_date_time DATETIME DEFAULT current_timestamp);

ALTER TABLE job
ADD FOREIGN KEY (labour_id) REFERENCES laborer(laborer_id);

ALTER TABLE job
ADD FOREIGN KEY (contractor_id) REFERENCES contractor(contractor_id);

create table user_activity (
        user_activity_id varchar(50) NOT NULL PRIMARY KEY,
        role_type varchar(10),
        activity_log varchar(2000),
        update_date_time DATETIME DEFAULT current_timestamp
);

commit;