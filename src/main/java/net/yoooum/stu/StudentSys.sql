drop database if exists student_sys;
create database if not exists student_sys;
use student_sys;

create table if not exists tb_user
(
    uid      int primary key auto_increment,
    username varchar(20) unique not null,
    password varchar(32) not null
);

create table if not exists tb_student
(
    sid  varchar(32) primary key,
    name varchar(20) not null,
    age  int         not null,
    sex  varchar(2) not null check (sex in('男','女'))
);

create table if not exists tb_course
(
    cid int primary key,
    name varchar(20) not null
);


insert into tb_user values (1, 'admin', 'admin');
select * from tb_user;

insert into tb_student values (10,'嘿嘿嘿',99,'男');
select * from tb_student;
UPDATE tb_student SET age=9 , sex='女' WHERE sid=1000000;
insert into tb_course values (1,'Linux操作系统'),(2,'Java程序设计'),(3,'计算机网络'),(4,'数据库技术'),(5,'大学英语Ⅱ');
select * from tb_course;