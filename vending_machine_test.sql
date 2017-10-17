create database vending_machine_test;

use vending_machine_test;

create table if not exists items (
item_id int not null auto_increment,
item_name varchar(30) not null,
item_price decimal(2 , 2) not null,
item_inventory int null,
primary key (item_id)
);