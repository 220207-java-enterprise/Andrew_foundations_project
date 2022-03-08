--CREATE TABLES

CREATE TABLE ers_reimbursements 
(
    REIMB_ID VARCHAR,
    AMOUNT NUMERIC (6,2) NOT NULL,
    SUBMITTED TIMESTAMP NOT NULL,
    RESOLVED TIMESTAMP,
    DESCRIPTION VARCHAR NOT NULL,
    RECEIPT BYTEA, --BLOB DOES not EXIST ISSUE
    PAYMENT_ID VARCHAR, --Prism don't focus on
    AUTHOR_ID VARCHAR NOT null,
    RESOLVER_ID VARCHAR, 
    STATUS_ID VARCHAR NOT null, 
    TYPE_ID VARCHAR NOT null, 
    constraint PK_REIMB_ID primary key (REIMB_ID)
);

create TABLE ers_users 
(
	USER_ID VARCHAR,
	USERNAME VARCHAR NOT NULL unique,
	EMAIL VARCHAR NOT NULL unique,
	USERPASSWORD VARCHAR NOT null,
	GIVEN_NAME VARCHAR NOT null,
	SUR_NAME VARCHAR not null,
	IS_ACTIVE BOOLEAN,
	ROLE_ID VARCHAR,--not unique
	constraint PK_USER_ID primary key (USER_ID)
);

create TABLE ers_reimbursements_statuses 
(
	STATUS_ID VARCHAR,
	STATUS VARCHAR unique,
	constraint PK_STATUS_ID primary key (STATUS_ID)
);

create TABLE ers_reimbursements_types 
(
	TYPE_ID VARCHAR,
	TYPEOF VARCHAR UNIQUE,
	constraint PK_TYPE_ID primary key (TYPE_ID)
);

create table ers_user_roles 
(
	ROLE_ID VARCHAR,
	ROLEOF VARCHAR unique,
	constraint PK_ROLE_ID primary key (ROLE_ID)
);

--FOR ERROR CHECKING
--drop TABLE ers_users, ERS_REIMBURSEMENTS, ERS_USER_ROLES;
--drop table ERS_REIMBURSEMENTS_TYPES;
--drop table ERS_REIMBURSEMENTS_STATUSES;

--ADD CONSTRAINTS

alter table ers_users add constraint FK_USER_ROLE_ID
	foreign key (ROLE_ID) references ERS_USER_ROLES(ROLE_ID) ON DELETE NO ACTION ON UPDATE NO action; --Fixme

alter table ers_user add constraint FK_
alter table ers_reimbursements add constraint FK_AUTHOR_USER_ID
	foreign key (AUTHOR_ID) references ERS_USERS(USER_ID) ON DELETE NO ACTION ON UPDATE NO action;
alter table ers_reimbursements add constraint FK_RESOLVER_USER_ID
	foreign key (RESOLVER_ID) references ERS_USERS(USER_ID) ON DELETE NO ACTION ON UPDATE NO action;
alter table ers_reimbursements add constraint FK_STATUS_ID
	foreign key (STATUS_ID) references ERS_REIMBURSEMENTS_STATUSES(STATUS_ID) ON DELETE NO ACTION ON UPDATE NO action;
alter table ers_reimbursements add constraint FK_TYPE_ID
	foreign key (TYPE_ID) references ERS_REIMBURSEMENTS_TYPES(TYPE_ID) ON DELETE NO ACTION ON UPDATE NO action;

--default setup
insert into ers_user_roles values ('1', 'Admin'), ('2', 'FManager'), ('3', 'Employee');
insert into ers_users values ('1', 'Tester99', 'AmazingTester@revature.com', 'p4$$W0RD', 'Amazing',
	'Tester', true, '1');
insert into ers_users values ('2', 'Administrator', 'Administrator@revature.com', 'p4$$W0RD', 'Administrator',
	'Administrator', true, '1');

select * from ers_users eu;

select * from ers_users eu;
select * from ers_user_roles eur;
SELECT USER_ID, USERNAME, EMAIL, USERPASSWORD, GIVEN_NAME, SUR_NAME, IS_ACTIVE, ers_users.ROLE_ID, ers_user_roles.ROLEOF FROM ers_users JOIN ers_user_roles ON ers_users.ROLE_ID = ers_user_roles.ROLE_ID;
