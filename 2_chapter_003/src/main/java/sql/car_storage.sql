--Задание.
--Нужно написать SQL скрипты:
--Создать структур данных в базе.
--Таблицы.
--   Кузов. Двигатель, Коробка передач.
--Создать структуру Машина. Машина не может существовать без данных из п.1.
--Заполнить таблицы через insert.
--Создать SQL запросы:
--1. Вывести список всех машин и все привязанные к ним детали.
--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

create database car_storage;

create table engine(
  id serial primary key,
  name varchar(100) not null
);

create table transmission(
  id serial primary key,
  name varchar(100) not null
);

create table body(
  id serial primary key,
  name varchar(100) not null
);

create table car(
  id serial primary key,
  name varchar(100) not null,
  engine_id int references engine(id) not null,
  transmisson_id int references transmission(id) not null,
  body_id int references body(id) not null
);

insert into engine(name) values
('engine_type_1'),
('engine_type_2'),
('engine_type_3'),
('engine_type_4'),
('engine_type_5'),
('engine_type_6');
insert into transmission(name) values
('transmission_type_1'),
('transmission_type_2'),
('transmission_type_3'),
('transmission_type_4'),
('transmission_type_5');
insert into body(name) values
('body_type_1'),
('body_type_2'),
('body_type_3'),
('body_type_4');
insert into car(name, engine_id, transmisson_id , body_id)
values
('car_1', 1, 1, 1),
('car_2', 2, 2, 2),
('car_3', 3, 3, 3),
('car_4', 2, 1, 3),
('car_5', 1, 2, 3),
('car_6', 1, 3, 2);

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name as car, b.name as body, e.name as engine, t.name as transmisson from car as c
join body as b on c.body_id = b.id
join engine e on c.engine_id = e.id
join transmission t on c.transmisson_id = t.id;
--2. Вывести отдельно детали, которые не используются в машине:
-- ----двигатели:
select e.name as "unused engine"
from car as c
right outer join engine as e on c.engine_id = e.id
where c.id is null
order by e.name;
----коробки передач:
select t.name as "unused transmisson"
from car as c
right outer join transmission as t on c.transmisson_id = t.id
where c.id is null
order by t.name;
----кузова:
select b.name as "unused body"
from car as c
right outer join body as b on c.body_id = b.id
where c.id is null
order by b.name;