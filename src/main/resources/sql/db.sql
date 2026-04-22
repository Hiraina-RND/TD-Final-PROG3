create database federation_db;

create user federation_db_manager with password '123456';

alter database federation_db owner to federation_db_manager;