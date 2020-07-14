CREATE TABLE user (
		 user_id INT NOT NULL AUTO_INCREMENT,
         role_type varchar(2) NOT NULL,
         user_name varchar(100) NOT NULL,
         password_hash varchar(100) NOT NULL,
         update_date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
         PRIMARY KEY(user_id));
         
CREATE TABLE laborer (
         laborer_id INT NOT NULL,
         parent_id INT,
         first_name varchar(100),
         last_name varchar(100),
         gender varchar(10),
         phone_number varchar(15),
         address varchar(500),
         aadhar_card_number varchar(50),
         aadhar_card_status varchar(5),
         pan_card varchar(20),
         skills varchar(200),
         active_ind varchar(2),
         preferred_job_location varchar(200),
         PRIMARY KEY(laborer_id),
         FOREIGN KEY (laborer_id) REFERENCES user(user_id));

CREATE TABLE contractor (
         contractor_id INT NOT NULL,
		 parent_id varchar(50),
         first_name varchar(100),
         last_name varchar(100),
         gender varchar(10),
         phone_number varchar(15),
         address varchar(500),
         aadhar_card_number varchar(50),
         aadhar_card_status varchar(5),
         pan_card varchar(20),
         skill varchar(200),
         active_ind varchar(2),
         preferred_job_location varchar(200),
         PRIMARY KEY(contractor_id),
         FOREIGN KEY (contractor_id) REFERENCES user(user_id));
         
CREATE TABLE job (
         job_id INT NOT NULL PRIMARY KEY,
         labour_id INT,
         contractor_id INT,
         active_ind varchar(2),
         update_date_time DATETIME DEFAULT current_timestamp,
         FOREIGN KEY (labour_id) REFERENCES laborer(laborer_id),
         FOREIGN KEY (contractor_id) REFERENCES contractor(contractor_id));

create table user_activity (
        user_activity_id INT NOT NULL PRIMARY KEY,
        role_type varchar(2),
        activity_log varchar(2000),
        update_date_time DATETIME DEFAULT current_timestamp
);
commit;