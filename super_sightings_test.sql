create database super_sightings_test;

use super_sightings_test;

create table superperson (
superPersonId int not null auto_increment,
superName varchar (255) not null,
superDescription text not null,
side tinyint not null,
powerId int not null,
primary key (superpersonId)
);

create table organization (
organizationId int not null auto_increment,
orgName varchar (255) not null,
locationId int not null,
phone varchar (45) not null,
primary key (organizationId)
);

create table superpersonorganization (
superPersonId int not null,
organizationId int not null,
primary key (superPersonId, organizationId)
);

alter table superpersonorganization 
add constraint fk_superpersonorganization_superperson
foreign key (superPersonId) references superperson(superPersonId);

alter table superpersonorganization 
add constraint fk_superpersonorganization_organization
foreign key (organizationId) references organization(organizationId);

create table power (
powerId int not null auto_increment,
powName varchar(45),
primary key (powerId)
);

alter table superperson
add constraint fk_superperson_power
foreign key (powerId) references power(powerId);

create table location (
locationId int not null auto_increment,
locationName text not null,
description text not null,
address text null,
latitude decimal(9,6) null,
longitude decimal(9,6) null,
primary key (locationId)
);

alter table organization
add constraint fk_organization_location
foreign key (locationId) references location (locationId);

create table sighting (
sightingId int not null auto_increment,
`date` date not null,
locationId int not null,
primary key (sightingId)
);

alter table sighting 
add constraint fk_sighting_location
foreign key (locationId) references location (locationId);

create table superpersonsighting (
superpersonId int not null,
sightingId int not null,
primary key (superpersonId, sightingId)
);

alter table superpersonsighting
add constraint fk_superpersonsighting_superperson
foreign key (superpersonId) references superperson(superpersonId);

alter table superpersonsighting
add constraint fk_superpersonsighting_sighting
foreign key (sightingId) references sighting(sightingId);