--liquibase formatted sql

--changeset karina:1

create table if not exists components
(
    id          serial primary key,
    name        varchar(255) not null,
    parameters  varchar(255) not null,
    reason      varchar(255) not null,
    status      varchar(255) not null,
    time        timestamp    not null
);