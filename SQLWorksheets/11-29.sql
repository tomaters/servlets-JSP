create table "visit" (
    "no" number(5,0) not null,
    "writer" varchar2(20) not null,
    "memo" varchar2(4000) not null,
    "regdate" date not null,
    constraint "visit_pk" primary key ("no")
);

create sequence visit_seq
    start with 1
    increment by 1
    nomaxvalue nocache nocycle; 
drop sequence visit_seq;

insert into visit(no, writer, memo, regdate) values (visit_seq.nextval, 'nameexample', 'memoexample', SYSDATE);
select * from visit;
delete from visit;
commit;

create table "login" (
    "id" varchar2(12) not null,
    "pass" varchar2(12) not null,
    constraint "login_pk" primary key("id")
);
