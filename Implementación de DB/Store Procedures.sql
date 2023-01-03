/*INSERTAR DATOS TABLA TRABAJADOR*/

delimiter $$
Create Procedure Insertar_Trabajador(in CodT int, in Nom varchar(30), in Ape varchar(30), in dni varchar(8), in sex varchar(20),
									in Fecha_Na date, in email varchar(50), in direccion varchar(70),
                                    in distrito varchar(70), in provincia varchar(70), in pais varchar(30),
                                    in sueldo double, in sis_pension varchar(30), in seguro double,
                                    in fecha_ingre datetime, in tipo varchar(30))
begin
	Insert into Trabajador(CodTrabajador, Nombre,Apellido,DNI,Sexo,Fecha_Nacimiento,Email,Direccion,Distrito,
						 Provincia,Pais,Sueldo,Sistema_pension,Seguro_salud,Fecha_ingreso,
                         Tipo_trabajador) 
	values(CodT, Nom, Ape, dni, sex, Fecha_na, email, direccion, distrito, provincia, pais, 
		   sueldo, sis_pension, seguro, fecha_ingre, tipo);
end;
$$

/*INSERTAR DATOS TABLA VENDEDOR*/

delimiter $$
Create Procedure Insertar_Vendedor(in codV int, in num_hijos int, in boni_hijos double)
begin
	Insert into Vendedor(CodVendedor, NHijos, boniHijos) 
	values(codV, num_hijos, boni_hijos);
end;
$$

/*INSERTAR DATOS TABLA ADMINISTRADOR*/

delimiter $$
Create Procedure Insertar_Administrador(in codA int, in monto_impuesto double, in utilidades double)
begin
	Insert into administrador(CodAdmin, Monto_Impuesto, Utilidades) 
	values(codA, monto_impuesto, utilidades);
end;
$$


/*MODIFICAR DATOS TABLA TRABAJADOR*/

delimiter $$
Create Procedure Modificar_Trabajador(in ema varchar(50), in direcc varchar(70), in provi varchar(70), in distri varchar(70) ,in suel double, in sistema varchar(30), in seguro double, in codT int)
begin
	update Trabajador set Email = ema, Direccion = direcc, Provincia = provi, Distrito = distri, Sueldo = suel, Sistema_pension = sistema, Seguro_salud = seguro where CodTrabajador = codT;
end;
$$

/*MODIFICAR DATOS TABLA VENDEDOR*/

delimiter $$
Create Procedure Modificar_Vendedor(in numH int, in boni double, in codV int)
begin
	update Vendedor set NHijos = numH, boniHijos = boni where CodVendedor = codV;
end;
$$

/*ELIMINAR DATOS TABLA TRABAJADOR*/

delimiter $$
Create Procedure Eliminar_Trabajador(in codT int)
begin
	delete from Trabajador where CodTrabajador  = codT;
end;
$$

/*ELIMINAR DATOS TABLA VENDEDOR*/

delimiter $$
Create Procedure Eliminar_Vendedor(in codV int)
begin
	delete from Vendedor where CodVendedor = codV;
end;
$$

/*INSERTAR DATOS TABLA USUARIO*/
delimiter $$
Create Procedure Insertar_Usuario(in codUsu int, in nombre_usu varchar(50), in contra varchar(30), in tipo varchar(30))
begin
	Insert into Usuario(CodUsuario, Nombre_Usu, Contraseña, Tipo_Usu) values(codUsu, nombre_usu, contra, tipo);
end;
$$

/*ELIMINAR DATOS TABLA USUARIO*/

delimiter $$
Create Procedure Eliminar_Usuario(in codU int)
begin
	delete from usuario where CodUsuario = codU;
end;
$$


/*INSERTAR DATOS TABLA DISTRIBUIDOR*/

delimiter $$
Create Procedure Insertar_Distribuidor(in codD int, in razon_s varchar(50), in ruc varchar(11), in surcu varchar(30), in direc varchar(30),
									   in correo varchar(30), in pagina varchar(70), in telefono varchar(50))
begin
	Insert into Distribuidor(CodDistribuidor, Razon_Social, RUC, Surcursal, Direccion, Correo_Contacto, Pagina_Web, Telefono_Contacto)
    values(codD, razon_s, ruc, surcu, direc, correo, pagina, telefono);
end;
$$

/*MODIFICAR DATOS TABLA DISTRIBUIDOR*/
delimiter $$
Create Procedure Modificar_Distribuidor(in codD int, in razon varchar(50), in surcu varchar(30), in dirrec varchar(30), in correo varchar(30), 
										pag varchar(70), in telef varchar(50))
begin
	update Distribuidor set Razon_Social = razon, Surcursal = surcu, Direccion = dirrec, Correo_Contacto = correo, Pagina_Web = pag, Telefono_Contacto = telef 
    where CodDistribuidor = codD;
end;
$$

/*ELIMINAR DATOS TABLA DISTRIBUIDOR*/
delimiter $$
Create Procedure Eliminar_Distribuidor(in codD int)
begin
	delete from Distribuidor where CodDistribuidor = codD;
end;
$$

/*INSERTAR DATOS TABLA PRODUCTO*/
delimiter $$
Create Procedure Insertar_Producto(in codP int, in codD int, in nom varchar(50), in color varchar(30), in tamaño varchar(30),
								   in stock int, in costo double, in precio double, in fecha_ingre date,
                                   in fecha_venc date)
begin
	Insert into Producto(CodProducto, CodDistribuidor, Nombre_Producto, Color, Tamaño, Stock, Costo_Prod, Precio_Prod, Fecha_Ingreso,
						Fecha_Vencimiento) 
	values(codP, codD, nom, color, tamaño, stock, costo, precio, fecha_ingre, fecha_venc);
end;
$$

/*MODIFICAR DATOS TABLA PRODUCTO*/

delimiter $$
Create Procedure Modificar_Producto(in codP int, in color varchar(30), in tamaño varchar(30), in stock int, in costo double, in precio double, in fechaI date)
begin
	update Producto set Color = color, Tamaño = tamaño, Stock = stock, Costo_Prod = costo, Precio_Prod = precio, Fecha_Ingreso = fechaI where CodProducto = codP;
end;
$$

/*MODIFICAR STOCK TABLA PRODUCTO*/
delimiter $$
Create Procedure Modificar_Stock(in codP int, in cantidad int)
begin
	update Producto set Stock = Stock - cantidad where CodProducto = codP;
end;
$$

/*ELIMINAR DATOS TABLA PRODUCTO*/
delimiter $$
Create Procedure Eliminar_Producto(in codP int)
begin
	delete from Producto where CodProducto = codP;
    delete from producto_consolidado where CodProducto = codP;
end;
$$


/*INSERTAR DATOS TABLA CLIENTE*/

delimiter $$
Create Procedure Insertar_Cliente(in codC int, in nom varchar(30), in ape varchar(30), in dni varchar(8), in ruc varchar(11), in sexo varchar(10),
								   in fecha_nacim date, in email varchar(50), in direc varchar(50), in distrito varchar(50),
								   in provincia varchar(50), in pais varchar(30))
begin
	Insert into Cliente(CodCliente, Nombre, Apellido, DNI, Ruc, Sexo, Fecha_Nacimiento, Email, Direccion, Distritos, Provincia, Pais) 
    values(codC, nom, ape, dni, ruc, sexo, fecha_nacim, email, direc, distrito, provincia, pais);
end;
$$

/*MODIFICAR DATOS TABLA CLIENTE*/

delimiter $$
Create Procedure Modificar_Cliente(in ruc varchar(11), in email varchar(50), in direcc varchar(50), in provin varchar(50), in distri varchar(50), in codC int)
begin
	update Cliente set Ruc = ruc, Email = email, Direccion = direcc, Provincia = provin, Distritos = distri where CodCliente = codC;
end;
$$

/*ELIMINAR DATOS TABLA CLIENTE*/

delimiter $$
Create Procedure Eliminar_Cliente(in codC int)
begin
	delete from Cliente where CodCliente = codC;
end;
$$


/*INSERTAR DATOS TABLA DETALLE_VENTA*/

delimiter $$
Create Procedure Insertar_DetalleVenta(in id int, in codDV int, in codV int, in codP int, in cantidadP int, in tipo varchar(30))
begin
	insert into DetalleVenta(ID, CodDetalleVenta, CodVendedor, CodProducto, CantidadProducto, Tipo) 
    values(id, codDV, codV, codP, cantidadP, tipo);
end;
$$

/*INSERTAR DATOS TABLA BOLETA*/

delimiter $$
Create Procedure Insertar_Boleta(in id int, in codB int, in total double)
begin
	Insert into Boleta(ID, CodBoleta, Total) values(id, codB, total);
end;
$$

/*INSERTAR DATOS TABLA FACTURA*/

delimiter $$
Create Procedure Insertar_Factura(in id int, in codF int, in subT double, in total double)
begin
	Insert into Factura(ID, CodFactura, SubTotal, Total) values(id, codF, subT, total);
end;
$$

/*INSERTAR DATOS TABLA VENTA*/

delimiter $$
Create Procedure Insertar_Venta(in codVt int, in codV int, in codC int, in monto double, in fecha_v datetime)
begin
	Insert into Venta(CodVenta, CodVendedor, CodCliente, Monto_Total, Fecha_Venta) 
    values(codVt, codV, codC, monto, fecha_v);
end;
$$
