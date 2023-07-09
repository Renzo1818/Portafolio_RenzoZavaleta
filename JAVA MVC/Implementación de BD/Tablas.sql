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


/*CREACION DE TABLA USUARIO*/

use db_ventas;
Create table Usuario(
CodUsuario int not null,
Nombre_Usu varchar(50) not null unique,
Contraseña varchar(30) not null,
Tipo_Usu varchar(30) not null,

primary key(CodUsuario),
foreign key(CodUsuario) references Trabajador(CodTrabajador)
);


/*CREACION DE TABLA CLIENTE*/

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


/*CREACION DE TABLA DISTRIBUIDOR*/

Create table Distribuidor(
CodDistribuidor int not null,
Razon_Social varchar(50) not null unique,
RUC varchar(11) not null unique,
Surcursal varchar(30) not null,
Direccion varchar(30) not null,
Correo_Contacto varchar(30) not null unique,
Pagina_Web varchar(70) not null unique,
Telefono_Contacto varchar(50) not null unique,

primary key(CodDistribuidor)
);

/*CREACION DE TABLA PRODUCTO*/

Create table Producto(
CodProducto int not null,
CodDistribuidor int not null,
Nombre_Producto varchar(50) not null unique,
Color varchar(30) not null,
Tamaño varchar(30) not null,
Stock int not null,
Costo_Prod double not null,
Precio_Prod double not null,
Fecha_Ingreso date not null,
Fecha_Vencimiento date not null,

primary key(CodProducto),
foreign key(CodDistribuidor) references distribuidor(CodDistribuidor)
);

/*CREACION DE TABLA VENTA*/

Create Table Venta(
CodVenta int not null auto_increment,
CodVendedor int not null,
CodCliente int not null,
Monto_Total double not null,
Fecha_Venta datetime not null,
Tipo varchar(30) not null,

primary key(CodVenta),
foreign key(CodVendedor) references Trabajador(CodTrabajador),
foreign key(CodCliente) references Cliente(CodCliente)
);

/*CREACION DE TABLA DETALLE_VENTA*/

Create Table DetalleVenta(
CodDetalleVenta int not null,
CodProducto int not null,
CantidadProducto int not null,


primary key(CodDetalleVenta, CodProducto),
foreign key(CodDetalleVenta) references Venta(CodDetalleVenta),
foreign key(CodProducto) references Producto(CodProducto)
);

/*CREACION DE TABLA BOLETA*/

Create Table Boleta(
CodBoleta int not null,
Total double not null,

primary key(CodBoleta),
foreign key(CodBoleta) references Venta(CodVenta)
);

/*CREACION DE TABLA FACTURA*/

create Table Factura(
CodFactura int not null,
SubTotal double not null,
Total double not null,

primary key(CodFactura),
foreign key(CodFactura) references Venta(CodVenta)
);
