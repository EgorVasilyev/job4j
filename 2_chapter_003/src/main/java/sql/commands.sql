--создать:
----базу данных
create database name_of_database;
----таблицу
create table name_of_table(
  name_of_arg1 type_of_arg,
  name_of_arg2 type_of_arg1,
  name_of_arg3 type_of_arg2 references name_of_other_table(name_of_arg_of_other_table)
);

--добавить:
----значение в таблицу:
insert into name_of_table(name_of_arg, ...) values(value_according_type_of_arg, ...);
------добавить столбец:
ALTER TABLE name_of_table ADD COLUMN name_of_arg;
------добавить столбец, ссылающийся на столбец из другой таблицы:
ALTER TABLE name_of_table ADD COLUMN name_of_arg type_of_arg references name_of_other_table(name_of_arg_of_other_table);

--изменить:
----таблицу
------изменить значение в столбце при условии
update name_of_table set name_of_arg = value_of_arg where name_of_other_arg = value_of_other_arg;

--удалить:
----таблицу:
DROP TABLE name_of_table;
----строку:
DELETE FROM name_of_table
WHERE name_of_arg=value_according_type_of_arg;
