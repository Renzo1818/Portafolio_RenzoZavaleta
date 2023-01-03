/*VISTA VENDEDOR CONSOLIDADO*/

use db_ventas;
Create view Vendedor_Consolidado as
Select v.CodVendedor,
	   t.Nombre,
       t.Apellido,
       t.DNI,
       t.Sexo,
       t.Fecha_Nacimiento,
       t.Email,
       t.Direccion,
       t.Distrito,
       t.Provincia,
       t.Pais,
       t.Sueldo,
       t.Sistema_pension,
       t.Seguro_salud,
       v.NHijos,
       v.boniHijos,
       t.Fecha_ingreso,
       t.Tipo_trabajador
from Vendedor v
inner join Trabajador t on v.CodVendedor = t.CodTrabajador;


/*VISTA ADMINISTRADOR CONSOLIDADO*/

Create view Admin_Consolidado as
Select ad.CodAdmin,
	   t.Nombre,
       t.Apellido,
       t.DNI,
       t.Sexo,
       t.Fecha_Nacimiento,
       t.Email,
       t.Direccion,
       t.Distrito,
       t.Provincia,
       t.Pais,
       t.Sueldo,
       t.Sistema_pension,
       t.Seguro_salud,
       ad.Monto_Impuesto,
	   ad.Utilidades,
       t.Fecha_ingreso,
       t.Tipo_trabajador
from Administrador ad
inner join Trabajador t on ad.CodAdmin = t.CodTrabajador;

select * from Vendedor_Consolidado;
select * from Admin_Consolidado;

select * from trabajador;
select * from vendedor;
select * from usuario;
select * from administrador;

select * from cliente;
select * from distribuidor;

 
 
/*VISTA PRODUCTO CONSOLIDADO*/

Create view Producto_Consolidado as
select p.CodProducto,
	   p.Nombre_Producto,
       p.Color,
       p.Tamaño,
       p.Stock,
       p.Costo_Prod,
       p.Precio_Prod,
       p.Fecha_Ingreso,
       p.Fecha_Vencimiento,
       d.Razon_Social
from Producto p
inner join Distribuidor d on p.CodDistribuidor = d.CodDistribuidor;	

select * from Producto_Consolidado;

select * from producto;


/*VISTA BOLETA CONSOLIDADO*/

Create view Boleta_Consolidado as
Select dv.ID,
	   dv.CodDetalleVenta,
	   dv.CodVendedor,
       dv.CodProducto,
       dv.CantidadProducto,
       p.Precio_Prod,
       b.Total,
       dv.Tipo
from Boleta b
inner join DetalleVenta dv on b.ID = dv.ID
inner join Producto p on dv.CodProducto = p.CodProducto
order by 1 asc;


/*VISTA FACTURA CONSOLIDADO*/

Create view Factura_Consolidado as
Select dv.ID,
	   dv.CodDetalleVenta,
	   dv.CodVendedor,
       dv.CodProducto,
       dv.CantidadProducto,
       p.Precio_Prod,
       f.Subtotal,
       f.Total,
       dv.Tipo
from Factura f
inner join DetalleVenta dv on f.ID = dv.ID
inner join Producto p on dv.CodProducto = p.CodProducto
order by 1 asc;


select * from Boleta_Consolidado;	
select * from Factura_Consolidado;
