
http://192.168.56.1:8082/login.do?jsessionid=121c18bfe7afe6e7479567a2e5f53108

drop table if exists member CASCADE;

create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    juso varchar(2048),
    primary key (id)
);

insert into member(name) values('spring');
insert into member(name) values('roian');


