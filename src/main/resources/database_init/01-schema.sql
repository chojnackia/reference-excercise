--liquibase formatted sql
--changeset ach:1
DROP TABLE IF EXISTS BOOKINGS;
CREATE TABLE BOOKINGS
(
    ID             uuid    not null,
    OWNER_ID       uuid,
    TENANT_ID      uuid,
    APARTMENT_ID   uuid,
    CHECK_IN_DATE  date,
    CHECK_OUT_DATE date,
    PRICE          integer not null,
    primary key (ID)
);

DROP TABLE IF EXISTS APARTMENTS;
create table APARTMENTS
(
    ID            UUID    NOT NULL,
    NAME          VARCHAR(255),
    PRICE_FOR_DAY INTEGER not null,
    DESCRIPTION   VARCHAR(255),
    SURFACE       DOUBLE  not null,
    OWNER_ID      UUID    not null,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS OWNERS;
CREATE TABLE OWNERS
(
    ID   UUID NOT NULL,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS TENANTS;
create table TENANTS
(
    ID   UUID NOT NULL,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
);

alter TABLE BOOKINGS
    add CONSTRAINT FKDW0XFNNTHBJ8AFP1IRA6SNDWQ
        foreign key (OWNER_ID) references OWNERS (ID);
alter table BOOKINGS
    add CONSTRAINT FKDW0XFNNTHBJ8AFP1IRA6SNDV
        foreign key (APARTMENT_ID) references APARTMENTS (ID);
alter table BOOKINGS
    add CONSTRAINT FKDW0XFNNTHBJ8AFP1IRA6SNDG
        foreign key (TENANT_ID) references TENANTS (ID);
alter table APARTMENTS
    add CONSTRAINT FKDW0XFNNTHBJ8AFP1IRA6SNDS
        foreign key (OWNER_ID) references OWNERS (ID);