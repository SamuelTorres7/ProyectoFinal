create table agente(
	ag_id int primary key,
    ag_nombre varchar(25),
    ag_apellido varchar(25),
    ag_tel varchar(16),
    ag_cp int
);
create table cliente(
	c_id int primary key,
    c_nombre varchar(25),
    c_apellido varchar(25),
    c_tel varchar(16),
    c_agente int,
    foreign key (c_agente) references agente(ag_id)
);
create table usuario(
	us_id int primary key,
    us_nombre varchar(10),
    us_password varchar(20)
);
insert into agente(ag_id,ag_nombre,ag_apellido,ag_tel,ag_cp) VALUES
(1,"Juan","Ruiz","4670000000",99940),
(2,"Ana","Martinez",4671000000,99960),
(3,"Pedro","Mercado","4671100000",99940);
insert into cliente(c_id,c_nombre,c_apellido,c_tel,c_agente) VALUES
(1,"Samuel","Torres","4671000001",1),
(2,"Alma","Medrano","4631009900",2),
(3,"Kevin","Montenegro","4660000000",3);