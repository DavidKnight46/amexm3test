-- CREATE Required Database
DROP DATABASE IF EXISTS amexexcerise;

--Create database
CREATE DATABASE amexexcerise;

-- CREATE Required Schema
CREATE SCHEMA IF NOT EXISTS amexschema;

-- CREATE required tables
CREATE TABLE if not exists amexschema.amexgiftcards(
                              id integer,
                              companyname varchar(50) NOT NULL,
                              value integer,
                              pointcost integer,
                              uuid varchar,
                              primary key (id));

-- CREATE sequence
create sequence if not exists amexschema.amex_seq;

--ALTER sequence
ALTER SEQUENCE IF EXISTS amexschema.amex_seq
    OWNED BY amexschema.amexgiftcards.id;