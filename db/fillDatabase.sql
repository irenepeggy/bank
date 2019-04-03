use bankdb;


insert into Client (typeClient)
	values ('person');
insert into PersonClient (name, surname, telephone, email, passport, idClient)
	values ('Daria', 'Ivanova', '+79998128912', 'ivanova@gmail.com', '1334 124548', 1);

insert into Client (typeClient)
	values ('person');
insert into PersonClient (name, surname, telephone, email, passport, idClient)
	values ('Alexander', 'Tsvetkov', '+79860018747', 'tsvetkov@gmail.com', '3542 176788', 2);

insert into Client (typeClient)
	values ('person');
insert into PersonClient (name, surname, telephone, email, passport, idClient)
	values ('Nicolay', 'Bobrov', '+79014450934', 'bobrov@gmail.com', '2514 567887', 3);

insert into Client (typeClient)
	values ('person');
insert into PersonClient (name, surname, telephone, email, passport, idClient)
	values ('Elena', 'Malysheva', '+79993110056', 'malysheva@gmail.com', '1367 253416', 4);

insert into Client (typeClient)
	values ('person');
insert into PersonClient (name, surname, telephone, email, passport, idClient)
	values ('Anastasia', 'Belina', '+79034559899', 'belina@gmail.com', '4524 154256', 5);
    
insert into Client (typeClient)
	values ('entity');
insert into EntityClient (name, kind, manager, idClient)
	values ('Teremok', 'restaurant chain', 'Malikova Sofia', 6);
    
insert into Client (typeClient)
	values ('entity');
insert into EntityClient (name, kind, manager, idClient)
	values ('H&M', ' apparel retail chain', 'Gobin Michael', 7);
    
insert into Client (typeClient)
	values ('entity');
insert into EntityClient (name, kind, manager, idClient)
	values ('X-Fit', 'fitness-centre chain ', 'Alieva Anna', 8);
    
insert into Client (typeClient)
	values ('entity');
insert into EntityClient (name, kind, manager, idClient)
	values ('Beauty bar Versal', 'beauty salon', 'Gomzin Andrey', 9);

insert into Client (typeClient)
	values ('entity');
insert into EntityClient (name, kind, manager, idClient)
	values ('Running Club Quicker', 'sport assosiation', 'Toropkov Igor', 10);
    
  
insert into ContactPerson (idEntityClient, name, number, email)
	values (1, 'Valin Peter', '+79785561256', 'terem_valin@gmail.com');
insert into ContactPerson (idEntityClient, name, number, email)
	values (2, 'Petrova Victoria', '+79257574390', 'hm_petrova@gmail.com');
insert into ContactPerson (idEntityClient, name, number, email)
	values (3, 'Panin Boris', '+79038997822', 'xfit_panin@gmail.com');
insert into ContactPerson (idEntityClient, name, number, email)
	values (4, 'Sokolova Maria', '+79655551089', 'versal_sokolova@gmail.com');
insert into ContactPerson (idEntityClient, name, number, email)
	values (5, 'Toropkov Igor', '+79251096735', 'quicker_toropkov@gmail.com');
    

insert into Department (name, address, contacts)
	values ('Moscow office', '', '+74991457621');
insert into Department (name, address, contacts)
	values ('St Petersburg office', '', '+74992347610');    
insert into Department (name, address, contacts)
	values ('Kazan office', '', '+74953924512');
insert into Department (name, address, contacts)
	values ('Vladivostok office', '', '+74952886457');
insert into Department (name, address, contacts)
	values ('Yakutsk office', '', '+74993671254');
    
insert into Schedule (idDepartment, mon, tue, wed, thu, fri, sat, sun)
	values (1, '09:00 - 18:00', '09:00 - 18:00', '09:00 - 18:00', '09:00 - 20:00', '09:00 - 18:00', '13:00 - 18:00', NULL);
insert into Schedule (idDepartment, mon, tue, wed, thu, fri, sat, sun)
	values (2, '10:00 - 19:00', '10:00 - 19:00', '10:00 - 18:00', '10:00 - 20:00', '10:00 - 18:00', '13:00 - 18:00', NULL);
insert into Schedule (idDepartment, mon, tue, wed, thu, fri, sat, sun)
	values (3, '09:00 - 18:00', '09:00 - 18:00', '09:00 - 18:00', '09:00 - 20:00', '09:00 - 18:00', '13:00 - 18:00', NULL);
insert into Schedule (idDepartment, mon, tue, wed, thu, fri, sat, sun)
	values (4, '09:00 - 18:00', '09:00 - 18:00', '09:00 - 18:00', '09:00 - 20:00', '09:00 - 18:00', '13:00 - 18:00', NULL);
insert into Schedule (idDepartment, mon, tue, wed, thu, fri, sat, sun)
	values (5, '09:00 - 18:00', '09:00 - 18:00', '09:00 - 18:00', '09:00 - 20:00', '09:00 - 18:00', '13:00 - 18:00', NULL);

insert into AccountType (name, maxAssessment, maxCancellation)
	values ('settlement', 1000000, 500000);
insert into AccountType (name, maxCredit,  interestOnLoan)
	values ('credit', 500000, 5);
insert into AccountType(name, minPeriod, maxPeriod, minDepositSum, interestOnDeposit)
	values ('deposit', 3, 12, 30000, 7);
    
insert into Account (idClient, idDepartment, accountNum, idType, status, debit, credit, openDate)
	values (1, 1, 22225555, 2, 'open', 100000, 60000, '2018-10-10');
insert into Account (idClient, idDepartment, accountNum, idType, status, deposit, period, openDate)
	values (2, 3, 33334324, 3, 'open', 150000, 6, '2019-01-23');
insert into Account (idClient, idDepartment, accountNum, idType, status, balance, openDate)
	values (5, 2, 11116583, 1, 'open', 1000000, '2018-04-13');
insert into Account (idClient, idDepartment, accountNum, idType, status, debit, credit, openDate, closeDate)
	values (6, 1, 22225555, 2, 'close', 150000, 0, '2018-07-10', '2018-12-10');
    
insert into Operation (idAccount, sum, time, idDepartment)
	values (1, 10000, '2018-11-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (1, 10000, '2018-12-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (1, 10000, '2018-01-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (1, 10000, '2018-02-10 12:00:00', 1);
    
insert into Operation (idAccount, sum, time, idDepartment)
	values (2, 150000, '2019-01-23 16:34:01', 3);

insert into Operation (idAccount, sum, time, idDepartment)
	values (3, 3000, '2018-05-23 10:14:09', 2);
insert into Operation (idAccount, sum, time, idDepartment)
	values (3, -100000, '2018-05-27 11:14:09', 2);
insert into Operation (idAccount, sum, time, idDepartment)
	values (3, 10500, '2018-06-27 18:19:00', 2);
    
insert into Operation (idAccount, sum, time, idDepartment)
	values (4, 30000, '2018-08-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (4, 30000, '2018-09-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (4, 30000, '2018-10-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (4, 30000, '2018-11-10 12:00:00', 1);
insert into Operation (idAccount, sum, time, idDepartment)
	values (4, 30000, '2018-12-10 12:00:00', 1);
    