create table collectivities
(
    id                  varchar(255) primary key,
    name                varchar(255),
    number              int,
    location            varchar(255) not null,
    federation_approval boolean default false
);

create type gender_type_enum as enum('MALE', 'FEMALE');

create table members
(
    id         varchar(255) primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    birth_date date         not null,
    gender     gender_type_enum  not null,
    address     varchar(255) not null,
    profession varchar(255) not null,
    phone_number varchar(255) not null,
    email      varchar(255)
);

create type occupation_type_enum as enum('JUNIOR','SENIOR', 'SECRETARY', 'TREASURER', 'VICE_PRESIDENT', 'PRESIDENT');

create table mandates (
    id int primary key,
    year integer,
    collectivity_id varchar(255) references collectivities(id) not null
);

create table mandate_members (
    id int primary key,
    member_id varchar(255) references members(id) not null,
    mandate_id int references mandates(id) not null,
    occupation occupation_type_enum not null
);

create table referees
(
    id          serial primary key,
    member_id   varchar(255) references members(id) not null,
    referee_id  varchar(255) references members(id) not null,
    relation_type varchar(255)
);

create type frequencyTypeEnum as enum('WEEKLY', 'MONTHLY', 'ANNUALLY', 'PUNCTUALLY');

create type statusTypeEnum as enum('ACTIVE', 'INACTIVE');

create table membership_fees (
    id varchar(255) primary key,
    eligible_from date,
    frequency frequencyTypeEnum not null,
    amount decimal,
    label text,
    status statusTypeEnum not null,
    collectivity_id varchar(255) references collectivities(id)
);