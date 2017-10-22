create database super_sitings;

use super_sitings;

create table superperson (
superPersonId int not null auto_increment,
superName varchar (255) not null,
superDescription text not null,
sideId int not null,
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

create table side (
sideId int not null auto_increment,
side varchar(45) not null,
primary key (sideId)
);

alter table superperson
add constraint fk_superperson_side
foreign key (sideId) references side(sideId);

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
add constraint fk_superpersonsighint_sighting
foreign key (sightingId) references sighting(sightingId);


insert into power (powName)
	values ('ride the lightning'),
		('witchburner'),
        ('kaleidoscopic vision');
        
insert into side (side)
	values ('hero'),
		('villian'),
        ('neutral');
        
insert into location (locationName, description, address, latitude, longitude)
	values ('Mordor', 'In the land where shadows lie', '1 Ring Circle', 29.977296, 31.132495),
		('The Mount', 'A classic hero story', '1,000 Faces Drive', 27.98785, 86.925026),
        ('Where?', 'The truth is out there', '51 Alley', 37.234894, -115.81082),
        ('Bodhi Tree', 'Within you, without you', 'It is everywhere', 24.695913, 84.991469),
        ('The Tomb', 'My buddy Christ Tomb', '3 Trinity Way', 31.783641, 35.224652),
        ('Fantoft Stave Church', 'Black metal burnings', 'Fantoftvegen 38, 5072 Bergen, Norway', 60.3393, 5.3533);
        
insert into sighting (date, locationId)
	values ('2017/05/03', 4),
    ('2017/09/01', 5),
    ('1945/05/05', 6);
    
insert into organization (orgName, locationId, phone)
	values ('Soothsayers', 2, 112-358-1321),
		('The Horsemen', 1, 666),
        ('Hofmanns Heros', 3, 419-1943);
        
insert into superperson (superName, superDescription, sideId, powerId)
	values ('JakeBlade', 'Shred Wizard', 1, 1),
		('Moksha', 'Merry Prankster', 3, 3),
        ('Gaahl', 'Black Death', 2, 2);
    
    