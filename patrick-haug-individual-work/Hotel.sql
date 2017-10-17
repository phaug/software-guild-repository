DROP DATABASE IF EXISTS Hotel;

CREATE DATABASE Hotel;

use Hotel;

create table Guest (
GuestID int not null auto_increment,
FirstName varchar(30) not null,
LastName varchar(40) not null,
Email varchar(50) not null,
Phone varchar(25) not null,
Age int not null,
BookingGuestID int null,
BookingID int not null,
primary key (GuestID)
);



create table Booking (
BookingID int not null auto_increment,
BookingStartDate date not null,
BookingEndDate date not null,
BillingID int not null,
primary key (BookingID)
);

alter table Guest
add foreign key (BookingID) references Booking(BookingID);

create table Room (
RoomID int not null auto_increment,
RoomNumber int not null,
RoomFloor int,
primary key (RoomID)
);

create table BookingRoom (
BookingID int not null,
RoomID int not null,
primary key (BookingID, RoomID)
);

alter table BookingRoom
add constraint fk_BookingRoom_Booking
foreign key (BookingID) references Booking(BookingID);

alter table BookingRoom
add constraint fk_BookingRoom_Room
foreign key (RoomID) references Room(RoomID);

create table RoomType (
RoomTypeID int not null auto_increment,
RoomOccupency int not null,
Bed varchar(20),
primary key (RoomTypeID)
);


create table Rates (
RateID int not null auto_increment,
Rates decimal (6, 2),
StartDate date not null,
EndDate date not null,
RoomTypeID int not null,
primary key (RateID),
foreign key (RoomTypeID) references RoomType(RoomTypeID)
);


create table Amenities (
AmenityID int not null auto_increment,
AmenityType varchar(30),
primary key (AmenityID)
);

create table RoomAmenities (
RoomID int not null,
AmenityID int not null,
primary key (RoomID, AmenityID)
);

alter table RoomAmenities
add constraint fk_Room_Amenities_Room
foreign key (RoomID) references Room(RoomID);

alter table RoomAmenities
add constraint fk_Room_Amenities_Amenities
foreign key (AmenityID) references Amenities(AmenityID);

create table Billing (
BillingID int not null auto_increment,
Damages decimal null,
Total decimal not null,
primary key (BillingID)
);

alter table Booking
add foreign key (BillingID) references Billing(BillingID);

create table Promotion (
PromotionID int not null auto_increment,
PromotionValue varchar(45),
PromotionType boolean not null,
primary key (PromotionID)
);

create table BillingPromotion (
BillingID int not null,
PromotionID int not null,
primary key (BillingID, PromotionID)
);

alter table BillingPromotion
add constraint fk_BillingPromotion_Billing
foreign key (BillingID) references Billing(BillingID);

alter table BillingPromotion
add constraint fk_BillingPromotion_Promotion
foreign key (PromotionID) references Promotion(PromotionID);

create table AddOn (
AddOnID int not null auto_increment,
AddOnType varchar(45),
AddOnCost decimal(6,2),
primary key (AddOnID)
);

create table BillingAddOn (
BillingID int not null,
AddOnID int not null,
primary key (BillingID, AddOnID)
);

alter table BillingAddOn
add constraint fk_BillingAddOn_Billing
foreign key (BillingID) references Billing(BillingID);

alter table BillingAddOn
add constraint fk_BillingAddOn_AddOn
foreign key (AddOnID) references AddOn(AddOnID);

use Hotel;

insert into guest (FirstName, LastName, Email, Phone, Age) 
	values ('Ler', 'Lalonde', 'ler@primus.com', '508-955-5545', '47'),
		('Les', 'Claypool', 'les@primus.com', '858-559-9954', '67'),
        ('Herb', 'Alexander', 'herb@primus.com', '554-958-5551', '5');
        
insert into booking (BookingDate)
	values ('2017/10/14'),
    ('2017/09/14'),
    ('2017/10/1'),
    ('2017/09/30');

insert into Room (RoomNumber, RoomFloor)
	values ('202', '2'),
		('101', '1'),
        ('333', '3'),
        ('440', '4');
        
        
insert into RoomType (RoomOccupency, Bed)
	values ('2', 'Double'),
    ('3', 'King&Twin'),
    ('4', '2Queen'),
    ('5', '2Double&Twin'),
    ('6', '2King&Double');

    
insert into Rates (StartDate, EndDate, RoomTypeID)
	values ('2017/10/13', '2017/10/16', '1'),
    ('2017/07/01', '2017/07/05', '3'),
    ('2017/12/23', '2017/12/26', '3'),
    ('2017/08/15', '2017/08/17', '4'),
    ('2017/08/15', '2017/08/17', '5');
    
insert into Amenities (AmenityType)
	values ('view'),
    ('jacuzzi'),
    ('safe');
    
insert into Billing (Damages, Total)
	values ('0.00', '140.30'),
    ('0.00', '70.50'),
    ('100.00', '250.00');
    
insert into Promotion (PromotionType, PromotionValue)
	values ('senior5%', true),
    ('10%', true),
    ('$100.00', false);
    
insert into AddOn (AddOnType, AddOnCost)
	values ('rollbed', '30.00'),
    ('roomservice', '15.00'),
    ('movierental', '10.00');
    

    
