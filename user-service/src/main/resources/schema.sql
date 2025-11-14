DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles(
    id serial primary key ,
    name varchar(20) not null
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY ,
    name varchar(200) NOT NULL ,
    username varchar(255) NOT NULL UNIQUE ,
    email varchar(255) NOT NULL UNIQUE ,
    password varchar(255) NOT NULL ,
    role_id int,
    created_at timestamp WITH TIME ZONE DEFAULT now(),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL
);

