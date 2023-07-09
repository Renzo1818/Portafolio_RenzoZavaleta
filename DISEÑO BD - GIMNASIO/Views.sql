/*CREACION DE LA VISTA CLIENTE SUSCRIPCION*/
CREATE VIEW ClienteSusDur AS  
SELECT cs.idCliSus,cs.idCliente,cs.idSuscripcion,sus.duracion_sus,sus.costo_sus   
FROM CLIENTE_SUSCRIPCION cs inner join SUSCRIPCION sus on cs.idSuscripcion=sus.idSuscripcion;



/*CREACION DE LA VISTA CLIENTE PRODUCTO*/
CREATE view CLIENTE_PRODUCTOS AS  
select f.idFactura, nombre_persona,apellido_persona,f.fecha_fact,df.tipo_concepto,pr.descripcion_pro,  
df.cantidad,df.subTotal  
from PERSONA p  
inner join FACTURA_CAB f on f.idCliente=p.idPersona  
inner join detalle_factura df on f.idFactura=df.idFactura  
inner join PRODUCTO pr on df.idProducto=pr.idProducto  
inner join CLIENTE c on p.idPersona=c.idPersona


  
/*CREACION DE LA VISTA CLIENTE TIPO SUSCRIPCION*/
CREATE VIEW CLIENTE_TIPO_SUSCRIPCION AS  
select cs.idCliSus, per.nombre_persona,per.apellido_persona, --muestra el nombre del cliente y el tipo de suscripcion que tiene  
sus.descripcion_sus, cs.fecha_suscripcion,cs.fecha_vencimiento, cs.vigencia,sus.duracion_sus from  
CLIENTE_SUSCRIPCION cs INNER JOIN PERSONA per on cs.idCliente=per.idPersona   
INNER JOIN SUSCRIPCION sus on cs.idSuscripcion=sus.idSuscripcion



/*CREACION DE LA VISTA FACTURA FINAL*/
create VIEW FACTURA_FINAL as  
  Select f.idFactura,f.fecha_fact, nombre_persona as nombre_cliente,apellido_persona as apellido_cliente,sum(subTotal) as subtotal,  
  c.ruc  
from PERSONA AS p  
Inner Join FACTURA_CAB as f on p.idPersona=f.idCliente  
Inner Join DETALLE_FACTURA as df on df.idFactura=f.idFactura  
inner join CLIENTE as c on p.idPersona=c.idPersona  
Group By f.idFactura,nombre_persona,apellido_persona,f.fecha_fact,c.ruc


/*CREACION DE LA TABLA LISTA CLIENTES*/
create view LISTA_CLIENTES AS --CLIENTES CON SU RESPECTIVO RUC  
SELECT p.idPersona as idCliente,p.dni_persona as dni_cliente,p.nombre_persona as nombre_cliente,   
p.apellido_persona as apellido_cliente, p.telefono_persona as telefono_cliente, c.RUC from   
PERSONA p inner join CLIENTE c on p.idPersona=c.idPersona


/*CREACION DE LA TABLA LISTA TRABAJADORES*/
CREATE VIEW LISTA_TRABAJADORES AS --TRABAJADORES CON SU RESPECTIVO TIPO DE SEGURO  
SELECT p.idPersona as idTrabajado, p.dni_persona as dni_trabajador,p.nombre_persona as nombre_trabajador,  
p.apellido_persona as apellido_trabajador, p.telefono_persona as telefono_trabajador, t.seguro_trabajador  
from PERSONA p inner join TRABAJADOR t on p.idPersona=t.idPersona
