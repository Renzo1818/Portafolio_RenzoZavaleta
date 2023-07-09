CREATE DATABASE GIMNASIO
GO

USE GIMNASIO
GO

/*CREACION DE LA TABLA PERSONA*/
create table PERSONA(
idPersona int not null identity(1,1),
dni_persona varchar(8) not null,
nombre_persona varchar(80) not null,
apellido_persona varchar(80)  not null,
telefono_persona varchar(80) not null,
tipo_persona int
)
GO
/*CONSTRAINTS*/
alter table PERSONA
add primary key(idPersona)
GO

/*CREACION DE LA TABLA TRABAJADOR*/
create table TRABAJADOR(
idPersona int not null,
seguro_trabajador varchar(10) 
)
GO

/*CONSTRAINTS*/
alter table TRABAJADOR
add constraint PK_idTrabajador primary key(idPersona)
GO
  
alter table Trabajador 
add constraint FK_idPersona foreign key(idPersona) references PERSONA
GO

/*CREACION DE LA TABLA CLIENTE*/
create table CLIENTE(
idPersona int not null,
RUC varchar(20) not null
)
GO

/*CONSTRAINTS*/
alter table CLIENTE
add constraint PK_idCliente primary key(idPersona)
GO
  
alter table CLIENTE
add constraint FK_idPersona_Cliente foreign key(idPersona) references PERSONA
GO


/*CREACION DE LA TABLA SUSCRIPCION*/
create table SUSCRIPCION (
idSuscripcion int not null identity(1,1),
descripcion_sus varchar(50) not null,
duracion_sus varchar(20) not null,
costo_sus FLOAT,
Estado int
)
GO

/*CONSTRAINTS*/
alter table SUSCRIPCION
add constraint PK_idSuscripcion primary key(idSuscripcion)
GO

alter table SUSCRIPCION
alter column descripcion_sus varchar(50)
go


/*CREACION DE LA TABLA PRODUCTO*/
create table PRODUCTO(
idProducto int not null identity(1000,1),
descripcion_pro varchar(20) not null,
preciou_pro float not null,
stock_pro int not null,
Esatdo int
)
GO

/*CONSTRAINTS*/
alter table PRODUCTO
add constraint PK_idProducto primary key(idProducto)
GO


/*CREACION DE LA TABLA CLIENTE SUSCRIPCION*/
create table CLIENTE_SUSCRIPCION(
idCliSus int not null identity(1,1),
idCliente int not null,
idSuscripcion int not null,
fecha_suscripcion varchar(20) not null,
fecha_vencimiento varchar(20) not null,
vigencia int
)
GO

/*CONSTRAINTS*/
alter table CLIENTE_SUSCRIPCION
ADD CONSTRAINT PK_idCliSus primary key(idCliSus)
GO
  
alter table CLIENTE_SUSCRIPCION
add constraint FK_idCliente foreign key(idCliente) references CLIENTE
GO
  
alter table CLIENTE_SUSCRIPCION
add constraint FK_idSuscripcion foreign key(idSuscripcion) references SUSCRIPCION
GO


/*CREACION DE LA TABLA DE CRONOGRAMA PAGOS*/
create table CRONOGRAMA(
idCuota int not null identity(1,1),
idCS int not null,
fecha_inicio varchar(20),
fecha_vencimiento varchar(20) not null,
monto_cuota float ,
Estado_cuota varchar(10) not null
)
GO

/*CONSTRAINTS*/
alter table CRONOGRAMA
add constraint PK_idCuota primary key(idCuota)
GO
  
alter table CRONOGRAMA
add constraint FK_idCS foreign key(idCS)  references CLIENTE_SUSCRIPCION


/*CREACION DE LA TABLA FACTURA*/
create table FACTURA_CAB(
idFactura varchar(10) not null,
idCliente int ,
idTrabajador int,
fecha_fact date,
Estado_fac int,
IGV float
)
GO

/*CONSTRAINTS*/
alter table FACTURA_CAB 
add constraint PK_idFactura primary key(idFactura)
GO
  
alter table  FACTURA_CAB
ADD CONSTRAINT FK_idCliente_FACTCAB FOREIGN KEY(idCliente) references CLIENTE
GO
  
ALTER TABLE FACTURA_CAB 
ADD CONSTRAINT FK_idTrabajadorFACTCAB foreign key(idTrabajador) references TRABAJADOR


/*CREACION DE LA TABLA DETALLE FACTURA*/
CREATE TABLE DETALLE_FACTURA(
idFactura varchar(10) not null,
tipo_concepto int not null,
idProducto int ,
idCuota int ,
cantidad int not null,
subTotal float not null
)
