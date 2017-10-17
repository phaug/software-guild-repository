create database vending_machine;

use vending_machine;

drop table items;

create table if not exists items (
item_id int not null auto_increment,
item_name varchar(30) not null,
item_price decimal(9 , 2) not null,
item_inventory int null,
primary key (item_id)
);

insert into items (item_name, item_price, item_inventory)
	values ('blotter paper', 5.00, 2);