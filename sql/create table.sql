CREATE TABLE user (
		 user_id INT PRIMARY KEY AUTO_INCREMENT,
         role_type varchar(2) NOT NULL,
         user_name varchar(100),
         password_hash varchar(100),
         update_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
         
CREATE TABLE laborer (
         laborer_id INT NOT NULL PRIMARY KEY, FOREIGN KEY (laborer_id) REFERENCES karamdb.user(user_id),
         parent_id varchar(50),
         first_name varchar(100),
         last_name varchar(100),
         gender varchar(10),
         age varchar(100),
         phone_number varchar(15),
         address varchar(500),
         adhar_card_number varchar(50),
         adhar_card_status varchar(5),
         pan_card varchar(20),
         skill varchar(200),
         active_ind varchar(2),
         preferred_job_location varchar(200)
);

CREATE TABLE contractor (
         contractor_id INT NOT NULL PRIMARY KEY, FOREIGN KEY (contractor_id) REFERENCES karamdb.user(user_id),
		 parent_id varchar(50),
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
         preferred_job_location varchar(200)
);
         
CREATE TABLE job (
         job_id INT NOT NULL PRIMARY KEY auto_increment,
         laborer_id INT NOT NULL,
         contractor_id INT NOT NULL,
         active_ind varchar(2),
         update_date_time DATETIME DEFAULT current_timestamp,
         FOREIGN KEY (contractor_id) REFERENCES karamdb.contractor(contractor_id),
         FOREIGN KEY (laborer_id) REFERENCES karamdb.laborer(laborer_id)
);

create table user_activity (
        user_activity_id INT NOT NULL PRIMARY KEY auto_increment,
        role_type varchar(10),
        activity_log varchar(2000),
        update_date_time DATETIME DEFAULT current_timestamp
);

commit;