create database bms;

use bms;

create table users(username varchar(10) primary key not null, password varchar(32), realname varchar(10), tel varchar(12), level tinyint(1));

create table readers(readerno int primary key not null auto_increment, readername varchar(15) not null, sex tinyint(1), birthday year, tel varchar(15), department varchar(50), remark varchar(80));

create table books(bookno int primary key not null auto_increment, type varchar(15) not null, name varchar(50) not null, author varchar(15), press varchar(15), isbn varchar(50), price float, number int default 1, time varchar(15), remark varchar(50));

create table borrow(borrowno int primary key not null auto_increment, bookno int not null references books(bookno), readerno int not null references readers(readerno), time varchar(15) not null, rtime varchar(15));