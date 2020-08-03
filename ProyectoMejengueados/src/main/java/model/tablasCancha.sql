create table cancha(
 id varchar (10) not null primary key,
 nombre varchar(30) not null,
 lugar varchar(30) not null,
 telefono varchar (30) not null
);

 insert into cancha values( '01','Locos por el futbol','San Jose', '2234-1530');
 insert into cancha values( '02','Actual Soccer','Cartago', '2551-3753');
 insert into cancha values( '03','Sportcenter','Cartago', '2552-3000');
 insert into cancha values( '04','Golds Gym','San Jose', '2271-0915');
 insert into cancha values( '05','Nisi','Heredia', '2244-8245');
 insert into cancha values( '06','Magic','San Jose', '4034-3985');
 insert into cancha values( '07','La Y griega','San Jose', '8323-4632');
 insert into cancha values( '08','Cibeles','San Jose', '8384-1559');
 insert into cancha values( '09','Extreme soccer','San Jose', '2280-1863');
 insert into cancha values( '10','El Estadio','San Jose', '2272 2121');


create table persona(
 cedula varchar (10) primary key not null,
 nombre varchar (20) not null,
 apellido1 varchar (15) not null,
 apellido2 varchar (15) not null,
 fechaNacimiento date not null,
 correo varchar (30) not null,
 celular varchar (10) not null
);

insert into persona values('101230456', 'Jose', 'Mora', 'Perez', '1992-05-10', 'juanmora@hotmail.com', '2515-2130'),
                   ('203210654', 'Maria', 'Lopez', 'Carmona', '1995-10-12', 'marialopez@gmail.com', '2571-4336'),
                   ('309870159', 'Juan', 'Mena', 'Hernandez', '1998-02-20', 'juanmena@gmail.com', '4025-5874'),
                   ('109510753', 'Julian', 'Fernandez', 'Calvo', '1993-04-22', 'julianfernandez@hotmail.com', '2612-6932'),
                   ('303570258', 'Hector', 'Cordero', 'Blanco', '1999-08-15', 'hectorcordero@hotmail.com', '2256-0101'),
                   ('108520952', 'Mariana', 'Quiros', 'Rojas', '2003-12-01', 'marianaquieros@gmail.com', '2714-3562'),
                   ('509630741', 'Andrea', 'Vargas', 'Badilla', '2002-05-03', 'andreavargas@gmail.com', '2201-5252'),
                   ('409540126', 'Oscar', 'Gomez', 'Fonseca', '2002-09-25', 'oscargomez@gmail.com', '2203-3484'),
                   ('703650856', 'Melania', 'Calderon', 'Ramirez', '2003-03-30', 'melaniacalderon@hotmail.com', '2552-4028'),
                   ('602370468', 'Gerardo', 'Pacheco', 'Hidalgo', '2001-10-10', 'gerardopacheco@hotmail.com', '2553-9875');




create table usuarioAdmin (
 idAdmin varchar (10) primary key not null,
 pwAdmin varchar(10) not null,
 nombreAdmin varchar (30) not null
);

create table usuarioRespaldo (
 idRespaldo varchar (10) primary key not null,
 pwRespaldo varchar(10) not null,
 nombreRespaldo varchar (30) not null
);

insert into usuarioAdmin values ('admin','admin','Administrador');
insert into usuarioRespaldo values ('respaldo','respaldo','Usuario de Respaldo');

insert into usuarioAdmin values ('admin1','456','Administrador1');
insert into usuarioRespaldo values ('respaldo1','654','Usuario de Respaldo 1');

SELECT count(*)
FROM PERSONA
WHERE FECHANACIMIENTO BETWEEN '1912-01-01' AND '2002-12-30'

create table edades(
id int primary key not null,
nombre varchar(10) not null,
cantidad double not null


);

insert into edades values(1,'Mayores',5),(2,'Menores',2);
select ID,NOMBRE,CANTIDAD from edades

create table reservas(
id int primary key not null,
nombre varchar(10) not null,
cantidad double not null
);
insert into reservas values(1,'lunes',15),(2,'Martes',10),(3,'Miercoles',12),(4,'Jueves',30),(5,'Viernes',35),(6,'Sabados',40),(7,'Domingos',32);

select ID,NOMBRE,CANTIDAD from reservas