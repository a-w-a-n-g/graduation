create database graduation;
use graduation;

create table department (
id tinyint unsigned not null primary key auto_increment,
name varchar(30) unique not null
);

create table major (
id tinyint unsigned not null primary key auto_increment,
name varchar(30) unique not null,
departmentId tinyint unsigned not null,
index(name),
foreign key (departmentId) references department (id) on delete cascade on update cascade
);

create table teacher (
id varchar(20) not null primary key,
name varchar(12) not null,
password varchar(16) not null default '123456',
sex tinyint(1) not null default '0',
title varchar(20) not null default '普通教师',
phone bigint(11) not null,
mail varchar(30),
index(name)
);	

create table topic (
id int unsigned not null primary key auto_increment,
title varchar(60) unique not null,
teacherId varchar(20) not null,
departmentName varchar(30) not null,
introduction varchar(600) not null,
requirement varchar(1200) not null,
insertTime datetime not null default CURRENT_TIMESTAMP,
ifPass boolean,
opinion varchar(300),
difficulty varchar(6) not null,
ifSelect boolean not null default false,
index(title),
foreign key (teacherId) references teacher (id) on delete cascade on update cascade
);

create table topic_modify (
id int unsigned not null primary key auto_increment,
title varchar(60) not null,
departmentName varchar(30) not null,
introduction varchar(600) not null,
requirement varchar(1200) not null,
opinion varchar(300),
difficulty varchar(6) not null,
modifyTime datetime not null default CURRENT_TIMESTAMP,
ifPass_tch boolean,
ifPass_admin boolean,
topicId int unsigned not null,
index(title),
index (ifHandle)
);

create table class (
id int unsigned not null primary key auto_increment,
name varchar(50) unique not null,
majorId tinyint unsigned not null,
index(name),
foreign key (majorId) references major (id) on delete cascade on update cascade
);

create table student (
 id bigint(13) not null primary key,
 name varchar(12) not null,
 password varchar(16) not null default '123456',
 sex tinyint(1) not null default '0',
 classId int unsigned not null,
 topicId int unsigned unique,
 phone bigint(11),
 mail varchar(30),
 index(name),
 foreign key (classId) references class (id) on delete cascade on update cascade,
 foreign key (topicId) references topic (id) on update cascade on delete set null	
 );

create table log_login (
id int unsigned not null primary key auto_increment,
identity tinyint(1) not null,
name varchar(12) not null,
entityId varchar(50) not null,
loginTime datetime not null default CURRENT_TIMESTAMP,
index(loginTime)
);

create table notice (
id int unsigned not null primary key auto_increment,
writer tinyint(1) not null,
writerName varchar(12),
target tinyint(1) not null,
content varchar(300) not null
);

create table administrator (
id int unsigned not null primary key auto_increment,
name varchar(10) not null,
password varchar(16) not null,
power boolean not null default false
);

create table description (
id int unsigned not null primary key auto_increment,
topicId int unsigned not null,
filePath varchar(100) not null,
commitTime datetime not null default CURRENT_TIMESTAMP,
ifPass boolean
);

create table bernal (
id int unsigned not null primary key auto_increment,
topicId int unsigned not null,
filePath varchar(100) not null,
commitTime datetime not null default CURRENT_TIMESTAMP,
ifPass boolean
);

create table interim_report (
id int unsigned not null primary key auto_increment,
topicId int unsigned not null,
filePath varchar(100) not null,
commitTime datetime not null default CURRENT_TIMESTAMP,
ifPass boolean
);

create table guidance_record (
id int unsigned not null primary key auto_increment,
topicId int unsigned not null,
filePath varchar(100) not null,
commitTime datetime not null default CURRENT_TIMESTAMP,
ifPass boolean
);

create table thesis_paper (
id int unsigned not null primary key auto_increment,
topicId int unsigned not null,
filePath varchar(100) not null,
commitTime datetime not null default CURRENT_TIMESTAMP,
ifPass boolean
);
