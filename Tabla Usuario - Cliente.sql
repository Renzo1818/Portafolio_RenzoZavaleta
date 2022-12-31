/*CREACION DE TABLA - USUARIO - CLIENTE*/

use db_ventas;
Create table Usuario(
CodUsuario int not null,
Nombre_Usu varchar(50) not null unique,
Contraseña varchar(30) not null,
Tipo_Usu varchar(30) not null,

primary key(CodUsuario),
foreign key(CodUsuario) references Trabajador(CodTrabajador)
);

create table Cliente(
CodCliente int not null,
Nombre varchar(30) not null,
Apellido varchar(30) not null,
DNI varchar(8) not null unique,
Ruc varchar(11),
Sexo varchar(10) not null,
Fecha_Nacimiento date not null,
Email varchar(50) not null unique,
Direccion varchar(50) not null,
Distritos varchar(50) not null,
Provincia varchar(50) not null,
Pais varchar(30) not null,

primary key(CodCliente)
);
