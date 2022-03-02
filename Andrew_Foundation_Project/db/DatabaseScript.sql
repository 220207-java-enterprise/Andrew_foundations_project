--CREATE TABLES

CREATE TABLE ERS_REIMBURSEMENTS
(
    REIMB_ID VARCHAR,
    AMOUNT NUMERIC (6,2) NOT NULL,
    SUBMITTED TIMESTAMP NOT NULL,
    RESOLVED TIMESTAMP,
    DESCRIPTION VARCHAR NOT NULL,
    RECEIPT BYTEA, --BLOB DOES not EXIST ISSUE
    PAYMENT_ID VARCHAR, --Prism don't focus on
    AUTHOR_ID VARCHAR NOT null, --need not be unique
    RESOLVER_ID VARCHAR, --need not be and NN unique 
    STATUS_ID VARCHAR NOT null, --not uniuqe
    TYPE_ID VARCHAR NOT null, --not unique
    constraint PK_REIMB_ID primary key (REIMB_ID)
);

create TABLE ERS_USERS
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

create TABLE ERS_REIMBURSEMENTS_STATUSES
(
	STATUS_ID VARCHAR,
	STATUS VARCHAR unique,
	constraint PK_STATUS_ID primary key (STATUS_ID)
);

create TABLE ERS_REIMBURSEMENTS_TYPES
(
	TYPE_ID VARCHAR,
	TYPEOF VARCHAR UNIQUE,
	constraint PK_TYPE_ID primary key (TYPE_ID)
);

create table ERS_USER_ROLES
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

alter table ers_reimbursements add constraint FK_AUTHOR_USER_ID
	foreign key (AUTHOR_ID) references ERS_USERS(USER_ID) ON DELETE NO ACTION ON UPDATE NO action;
alter table ers_reimbursements add constraint FK_RESOLVER_USER_ID
	foreign key (RESOLVER_ID) references ERS_USERS(USER_ID) ON DELETE NO ACTION ON UPDATE NO action;
alter table ers_reimbursements add constraint FK_STATUS_ID
	foreign key (STATUS_ID) references ERS_REIMBURSEMENTS_STATUSES(STATUS_ID) ON DELETE NO ACTION ON UPDATE NO action;
alter table ers_reimbursements add constraint FK_TYPE_ID
	foreign key (TYPE_ID) references ERS_REIMBURSEMENTS_TYPES(TYPE_ID) ON DELETE NO ACTION ON UPDATE NO action;

