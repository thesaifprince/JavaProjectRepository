create database hms;
drop database hms;

use hms;

create table register_patients(
id int primary key auto_increment,
name varchar(30) not null,
age int not null,
gender varchar(10),
contact varchar(12));

select * from register_patients;

create table appointments(
id int primary key auto_increment,
pname varchar(30) not null,
dname varchar(40) not null,
appointment_date DATE 
);

drop table appointments;
Select pname,dname,appointment_date from appointments order by id desc;
select * from appointments;
 -- INSERT INTO book (id, name, hire_date)
-- VALUES (1, 'Alice', STR_TO_DATE('08-11-2025', '%d-%m-%Y'));

USE hms;

CREATE TABLE billing (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(50) NOT NULL,
    doctor_name VARCHAR(50) NOT NULL,
    appointment_date DATE NOT NULL,
    consultation_fee DECIMAL(10,2),
    medicine_charges DECIMAL(10,2),
    room_charges DECIMAL(10,2),
    total_amount DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM billing ORDER BY bill_id DESC;

CREATE TABLE pdf_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(40),
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from pdf_log