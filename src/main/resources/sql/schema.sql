create table collectivity
(
    id                  varchar(255) primary key,
    name                varchar(255),
    location            varchar(255) not null,
    federation_approval boolean default false
);

create type gender_type_enum as enum('MALE', 'FEMALE');

create table member
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

create table collectivity_member
(
    id serial       primary key,
    member_id       varchar(255) references member(id) not null,
    collectivity_id varchar(255) references collectivity(id) not null,
    occupation            occupation_type_enum not null
);

ALTER TABLE collectivity_member
ADD CONSTRAINT unique_member_collectivity
UNIQUE (member_id, collectivity_id);

create table referees
(
    id          serial primary key,
    member_id   varchar(255) references member(id),
    referee_id  varchar(255) references member(id)
);