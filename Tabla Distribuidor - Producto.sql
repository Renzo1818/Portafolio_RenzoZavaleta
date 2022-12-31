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
