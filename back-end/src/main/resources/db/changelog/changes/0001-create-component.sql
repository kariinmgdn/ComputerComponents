--liquibase formatted sql

--changeset karina:1

create table if not exists component
(
    id         serial primary key,
    name       varchar(255) not null,
    parameters varchar(255) not null,
    reason     varchar(255) not null,
    status     varchar(255) not null,
    time       varchar(255) not null
);