drop database if exists todo_prod;
create database todo_prod;

use todo_prod;

create table users (

userId int primary key auto_increment,
username varchar(300) not null unique,
`password` varchar(2048)not null
);

create table todos (

todoId int primary key auto_increment,
todoText text not null,
authorId int not null,
isPublic bit(1) not null,
createDate date not null,
constraint fk_todos_users foreign key(authorId) references users (userId)
);



create table roles (

roleId int primary key auto_increment,
roleName varchar(20) not null unique


);


create table userroles (

userId int not null,
roleId int not null,
constraint pk_userroles primary key (userId, roleId),
constraint fk_users_userroles foreign key (userId) references users (userId),
constraint fk_roles_userroles foreign key (roleId) references roles (roleId)
);


insert into users (username, `password`) 
values ('bob', '$2a$12$K./TSEIs3HAtzpf2xM0h9.SCIEzhBYK0VrhFlX5KNjqCJ4VkmCmj2');

insert into users (username, `password`) 
values ('june', '$2a$12$86VIk8ecC1SynL1Q2YRqvOC/5O25GyWUif.oEg4SZ1JuF5lCUOkPS');

insert into roles (roleName) values ('AUTHOR'), ('ADMIN');

insert into userroles (userId, roleId) values (1, 1), (2, 2);

insert into todos (todoText, authorId, isPublic, createDate) values ('this is a private todo', 1, 0, '2020-04-06'), ('this is a public todo', 2, 1, '2020-04-05');






