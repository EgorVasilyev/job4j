---------
-- Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.
---------
create database items_system;
create table rules(
  id serial primary key,
  name varchar(2000)
);
insert into rules(name) values('first_rule');
create table role(
  id serial primary key,
  name varchar(2000)
);
insert into role(name) values('first_role');
create table role_rules(
  role_id int references role(id),
  rules_id int references rules(id)
);
insert into role_rules(role_id, rules_id) values(1, 1);
create table users(
  id serial primary key,
  name varchar(2000),
  role_id int references role(id)
);
insert into users(name) values('first_user');
create table category(
  id serial primary key,
  name varchar(2000)
);
insert into category(name) values('first_category');
create table state(
  id serial primary key,
  name varchar(2000)
);
insert into state(name) values('first_state');
create table item(
  id serial primary key,
  name varchar(2000),
  user_id int references users(id),
  category_id int references category(id),
  state_id int references state(id)
);
insert into item(name, user_id, category_id, state_id) values('first_item', 1, 1, 1);
ALTER TABLE users ADD COLUMN item_id int references item(id);
update users set item_id = 1 where id = 1;
create table comments(
  id serial primary key,
  name varchar(2000),
  item_id int references item(id)
);
insert into comments(name, item_id) values('first_comment', 1);
create table attach(
  id serial primary key,
  name varchar(2000),
  item_id int references item(id)
);
insert into attach(name, item_id) values('first_attach', 1);
