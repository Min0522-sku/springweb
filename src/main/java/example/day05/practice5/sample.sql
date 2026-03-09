drop database if exists practice5;
create database practice5;
use practice5;

create table book(
	bno int auto_increment primary key,
    bname varchar(100) not null,
    bauthor varchar(50) not null,
    bpublisher varchar(255)
);

insert into book(bname, bauthor, bpublisher) values("자바책", "유재석", "안양출판사");
insert into book(bname, bauthor, bpublisher) values("파이썬책", "강재석", "서울출판사");
insert into book(bname, bauthor, bpublisher) values("스프링책", "유재석", "안양출판사");
select * from book;