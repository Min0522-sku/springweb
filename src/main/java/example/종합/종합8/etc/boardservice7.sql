drop database if exists boardservice7;
create database boardservice7;
use boardservice7;
create table board(
	bno int auto_increment,
    constraint primary key(bno),
    bcontent longtext not null,
    bwriter varchar(30) not null,
    bdate datetime default now()
);

insert into board(bcontent, bwriter) values('게시물 내용 1입니다.', '김철수');
insert into board(bcontent, bwriter) values('게시물 내용 2입니다.', '이영희');
insert into board(bcontent, bwriter) values('게시물 내용 3입니다.', '박민수');
insert into board(bcontent, bwriter) values('게시물 내용 4입니다.', '최유진');
insert into board(bcontent, bwriter) values('게시물 내용 5입니다.', '정지훈');
insert into board(bcontent, bwriter) values('게시물 내용 6입니다.', '강호동');
insert into board(bcontent, bwriter) values('게시물 내용 7입니다.', '유재석');
insert into board(bcontent, bwriter) values('게시물 내용 8입니다.', '신동엽');
insert into board(bcontent, bwriter) values('게시물 내용 9입니다.', '송중기');
insert into board(bcontent, bwriter) values('게시물 내용 10입니다.', '전지현');
select * from board;