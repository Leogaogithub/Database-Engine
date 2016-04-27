1 assumption: directroy ./database  should exist and be in the same directory with leoBase.jar.

2  launch the application use command: java -jar leoBase.jar

3. LeoBase  is the source code directory.

4. compile the resource.  I use the main function  in LeoBaseSysetm.java as the main function of the project.

5. here is is some examples to show you how to use sql:

show schemas;
show tables;
help;
version;
exit;
select * from columns;
select * from tables;
select * from schemata;

show schemas;
show tables;
CREATE SCHEMA University;
USE University;
CREATE TABLE Students (id int primary key, name varchar(25), bdate date, credits short);
INSERT INTO Students VALUES(1,'Jason Day','1995-08-21',32);
INSERT INTO Students VALUES(2,'Shirley Moore','1994-12-19',63);
INSERT INTO Students VALUES(4,'Brenda Webb','1993-12-18',29);
INSERT INTO Students VALUES(5,'Lois Simmons','1994-05-14',15);
INSERT INTO Students VALUES(3,'Tina Bell','1995-06-16',45);
SELECT * FROM Students WHERE id=3;
SELECT * FROM Students WHERE credits>30;
SELECT * FROM Students WHERE bdate<'1995-01-01';

drop table Students;