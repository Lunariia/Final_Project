Create database pharmacy character set utf8 collate utf8_bin;

use pharmacy;

create table Users
	(
		id bigint auto_increment,
		login varchar(60) not null,
		password varchar(40) not null,
		role varchar(10),
		first_name varchar(20) not null,
		last_name varchar(40) not null,
		balance decimal(5,2) not null default 100,
		primary key (id)
	);

	create table Medicines
	(
		id bigint auto_increment,
		title varchar(100) not null,
		dosage decimal(6,2) not null,
		cost decimal(10,2) not null,
		formula boolean not null default 0,
		quantity int,
		primary key (id)
	);

create table Prescriptions
	(
		id bigint auto_increment,
		user_id bigint not null,
		medicine_id bigint not null,
		start_date date,
		end_date date,
		access boolean,
		primary key (id),
		foreign key (user_id) references Users (id),
		foreign key (medicine_id) references Medicines (id)
	);

create table Purchases
	(
		id bigint auto_increment,
		user_id bigint not null,
		medicine_id bigint not null,
		amount int not null,
		date_of_purchase varchar(20),
		primary key (id),
		foreign key (user_id) references Users (id),
		foreign key (medicine_id) references Medicines (id)
	);


	create index name_index on Users (login);
    alter table prescriptions add column access boolean not null default 0;