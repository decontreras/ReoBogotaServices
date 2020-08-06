CREATE DATABASE RETOBOGOTA;
USE RETOBOGOTA;
CREATE TABLE LOCATION (
	name varchar(10) primary key,
    area_m2 int,
    parent_location varchar (10),
    foreign key (parent_location) REFERENCES LOCATION (name)
);
insert into LOCATION values ("default", 2, "default");