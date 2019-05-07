--Задание.
--1. Написать запрос получение всех продуктов с типом "СЫР"
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
--4. Написать запрос, который выводит самый дорогой продукт.
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
--8. Вывести все продукты и их тип.

--product(id, name, type_id, expired_date, price)
--type(id, name)

create database shop;

create table type(
  id serial primary key,
  name varchar(100)
);

create table product(
  id serial primary key,
  name varchar(100),
  type_id int references type(id),
  expired_date date,
  price int
);

insert into type(name) values('СЫР'),('МОЛОКО'),('ФРУКТЫ'),('ОВОЩИ'),('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price)
values('Простоквашино', 2, '25/5/2019', 70)
,('Домик в деревне', 2, '1/6/2019', 60)
,('Бананы', 3, '10/5/2019', 55)
,('Картошка', 4, '8/9/2019', 63)
,('Апельсины', 3, '2/5/2019', 96)
,('Российский', 1, '12/5/2019', 110)
,('Свекла', 4, '6/7/2019', 40)
,('Морковь', 4, '8/8/2019', 15)
,('Ламбер', 1, '14/5/2019', 200)
,('Эдам', 1, '30/4/2019', 160)
,('мороженое Эскимо', 5, '26/4/2019', 35)
,('мороженое 48 копеек', 5, '28/4/2019', 85);
--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.id, p.name, p.expired_date, t.name as type, p.price
from product as p join type as t
on p.type_id=t.id
where upper(t.name) = 'СЫР';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like '%мороженое%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where EXTRACT(month FROM expired_date)=EXTRACT(month FROM now()) + 1;
--4. Написать запрос, который выводит самый дорогой продукт.
select * from product where price=(select MAX(price) from product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(p.id)
from product as p join type as t
on p.type_id=t.id
where upper(t.name) = 'ОВОЩИ'
group by t.name;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.id, p.name, p.expired_date, t.name as type, p.price
from product as p join type as t
on p.type_id=t.id
where upper(t.name) = 'СЫР' or upper(t.name) = 'МОЛОКО';
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 3 штук.
select t.name as "Name of type", count(p.name) as "Count of products"
from product as p join type as t
on p.type_id=t.id
group by t.name
HAVING count(p.name)<3
order by t.name;
--8. Вывести все продукты и их тип.
select p.name, t.name as type
from product as p join type as t
on p.type_id=t.id


