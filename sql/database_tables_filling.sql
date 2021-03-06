	use pharmacy;

	insert into Users(login,password,role,first_name,last_name,balance)
	values('firstUser',SHA1('first'),'CUSTOMER','Andrei','Ermalaev',100),
		  ('secondUser',SHA1('second'),'CUSTOMER','Maria','Andreichuc',100),
		  ('thirdUser',SHA1('third'),'CUSTOMER','Jordan','Gardner',100),
		  ('forthUser',SHA1('forth'),'CUSTOMER','Donald','Edwards',100),
		  ('fifthUser',SHA1('fifth'),'CUSTOMER','Oswald','Jackson',100),
		  ('doctorUser',SHA1('doctor'),'DOCTOR','Zoe','Small',100),
		  ('emloyeeUser',SHA1('emloyee'),'WORKER','Julian','Bryan',100);

	insert into Medicines(title,dosage,cost,formula,quantity)
	values('Alprazolam',0.7,10.30,1,10),
		  ('Marshmallow',0.5,32.10,1,10),
		  ('Ambroxol',0.4,15,0,10),
		  ('Amiodaronum',0.4,12.50,0,10),
		  ('Analgin',0.3,13.20,1,10),
		  ('Analgin',0.5,10.20,1,10),
		  ('Biseptolum',0.6,3.20,0,10),
		  ('Витабакт',0.1,5.60,0,10);

insert into Prescriptions(user_id,medicine_id,start_date,end_date)
values(1,6,DATE_FORMAT('12-06-20','%d/%m/%y'),DATE_FORMAT('12-06-21','%d/%m/%y'));

insert into Prescriptions(user_id,medicine_id,start_date,end_date)
values(9,2,DATE_FORMAT('25-01-21','%d/%m/%y'),DATE_FORMAT('25-01-22','%d/%m/%y'));