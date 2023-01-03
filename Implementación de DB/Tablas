/*CREACION DE DB*/
create database DB_Ventas;
use DB_Ventas;

/*CREACION DE TABLA TRABAJADOR*/
create table Trabajador(
CodTrabajador int not null,
Nombre varchar(30) not null,
Apellido varchar(30) not null,
DNI varchar(8) not null unique,
Sexo varchar(20) not null,
Fecha_Nacimiento date not null,
Email varchar(50) not null unique,
Direccion varchar(70) not null,
Distrito varchar(70) not null,
Provincia varchar(70) not null,
Pais varchar(30) not null,
Sueldo double not null,
Sistema_pension varchar(30) not null,
Seguro_salud double not null,
Fecha_ingreso datetime not null,
Tipo_trabajador varchar(30) not null,

primary key(CodTrabajador)
);

/*CREACION DE TABLA VENDEDOR*/

create table vendedor(
CodVendedor int not null,
NHijos int not null,
boniHijos double not null,

primary key(CodVendedor),
foreign key(CodVendedor) references Trabajador(CodTrabajador)
);

/*CREACION DE TABLA ADMINISTRADOR*/

create table Administrador(
CodAdmin int not null,
Monto_Impuesto double not null,
Utilidades double not null,

primary key(CodAdmin),
foreign key(CodAdmin) references Trabajador(CodTrabajador)
);
