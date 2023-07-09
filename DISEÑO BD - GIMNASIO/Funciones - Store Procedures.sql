/*FUNCIONES*/
CREATE FUNCTION duracion (@idSuscripcion INT)  
RETURNS INT  
AS  
BEGIN  
 declare @duracion int;  
 select @duracion =duracion_sus from SUSCRIPCION where idSuscripcion=@idSuscripcion;  
    RETURN @duracion;  
END;


CREATE FUNCTION ObtenerFechaHoy()  
RETURNS DATE  
AS  
BEGIN  
    DECLARE @fechaHoy DATE;  
    SET @fechaHoy = CAST(GETDATE() AS DATE);  
    RETURN @fechaHoy;  
END;


/*STORE PROCEDURES*/

/*CREACION DE SP_CREAR_CLIENTE*/
CREATE procedure SP_CREAR_CLIENTE   
(  
 @dni_cliente varchar(8) ='',  
 @nombre_cliente varchar(80) ='',  
 @apellido_cliente varchar(80) ='',  
 @telefono_cliente varchar(80)='',  
 @ruc varchar(20),  
 @idSuscripcion int,  
 @idTrabajador int,  
 @tipo_concepto int,  
 @idProducto int,  
 --@idCuota int,  
 @cantidad int  
)  
as begin  
 --CREAR CLIENTE NUEVO Y SU RESPECTIVA SUSCRIPCION  
 DECLARE @idPersona int =0  
 declare @tipo_persona int;  
 --Insertar en Persona  
 insert into PERSONA(dni_persona,nombre_persona,apellido_persona,telefono_persona,tipo_persona)   
 values(@dni_cliente,@nombre_cliente,@apellido_cliente,@telefono_cliente,1)  
  
 SET @idPersona = @@IDENTITY   
 SELECT @idPersona AS idPersona  
 --Insertar en cliente  
 insert into Cliente(idPersona,RUC)   
 values(@idPersona,@ruc)  
  
 --Insertar CLIENTE_SUSCRIPCION  
 declare @duracion int;   
 set @duracion= dbo.duracion(@idSuscripcion);  
  
 DECLARE @fecha_inicio DATE;  
 --SET @fecha_inicio = CONVERT(DATETIME, SWITCHOFFSET(GETDATE(), DATEPART(TZOFFSET, SYSDATETIMEOFFSET())));  
 SET @fecha_inicio = dbo.ObtenerFechaHoy();  
  
 declare @fecha_vencimiento date;  
 SET @fecha_vencimiento = DATEADD(MONTH, @duracion, @fecha_inicio);  
   
 insert into CLIENTE_SUSCRIPCION(idCliente,idSuscripcion,fecha_suscripcion,fecha_vencimiento,vigencia)  
 values(@idPersona,@idSuscripcion,@fecha_inicio,@fecha_vencimiento,1)  
   
 --Generar cronograma  
  
 declare @idCliSus int;   
 select @idCliSus=idCliSus from CLIENTE_SUSCRIPCION  where idCliente=@idPersona;  
   
 declare @iddursus int;  
 select @iddursus=duracion_sus from dbo.ClienteSusDur where idCliSus=@idCliSus;  
   
 declare @monto float;  
 select @monto=costo_sus from dbo.ClienteSusDur where idCliSus=@idCliSus;  
  
 declare @contador int=1;  
 while @contador<=@iddursus begin  
  if(@contador=1)begin  
  select @fecha_inicio=fecha_suscripcion from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  
  SET @fecha_vencimiento = DATEADD(MONTH, @contador, @fecha_inicio);  
  
  insert into CRONOGRAMA(idCS,fecha_vencimiento,monto_cuota,Estado_cuota,fecha_inicio)   
  values(@idCliSus,@fecha_vencimiento,@monto,'PENDIENTE',@fecha_inicio);  
  SET @contador = @contador + 1;   
  end    
  else begin   
  declare @fecha_suscripcion date;  
  select @fecha_suscripcion =fecha_suscripcion from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  select @fecha_inicio=fecha_suscripcion from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  SET @fecha_inicio=DATEADD(MONTH,@contador-1, @fecha_inicio); --declare @fecha_inicio date;  
  set @fecha_vencimiento = DATEADD(MONTH,@contador, @fecha_suscripcion);--declare @fecha_vencimiento date;  
   
  insert into CRONOGRAMA(idCS,fecha_vencimiento,monto_cuota,Estado_cuota,fecha_inicio)   
  values(@idCliSus,@fecha_vencimiento,@monto,'PENDIENTE',@fecha_inicio);  
  SET @contador = @contador + 1;   
  end  
 end  
 --Insertar FACTURA CABECERA  
  
 declare @idFactura varchar(10);  
 declare @numero int;  
 select @numero = COUNT(*) from FACTURA_CAB;   
 set @numero = @numero + 1;  
 set @idFactura ='FA' + RIGHT('000'+CAST(@numero as varchar(3)),3);  
  
 DECLARE @fecha_fact DATE;  
 --SET @fecha_fact = CONVERT(DATETIME, SWITCHOFFSET(GETDATE(), DATEPART(TZOFFSET, SYSDATETIMEOFFSET())));  
 SET @fecha_fact = dbo.ObtenerFechaHoy();  
  
 insert into FACTURA_CAB(idFactura,idCliente,idTrabajador,fecha_fact,Estado_fac,IGV)  
 values(@idFactura,@idPersona,@idTrabajador,@fecha_fact,1,0.18)  
end





/*CREACION DE SP_SEL_HISTORIA_CLIENTE*/
CREATE procedure SP_SEL_HISTORIA_CLIENTE   
(  
@dni_cliente varchar(8)  
)   
as begin  
 declare @idPersona int;  
 select @idPersona= idPersona from PERSONA where dni_persona=@dni_cliente;   
  
 declare @idCliSus int;  
 select @idCliSus=idCliSus from CLIENTE_SUSCRIPCION where idCliente=@idPersona;  
   
 DECLARE @fecha_hoy DATE;  
 --SET @fecha_hoy = CONVERT(DATETIME, SWITCHOFFSET(GETDATE(), DATEPART(TZOFFSET, SYSDATETIMEOFFSET())));  
 SET @fecha_hoy = dbo.ObtenerFechaHoy();  
  
 declare @fecha_vencimiento date;  
 select @fecha_vencimiento= fecha_vencimiento from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  
 if(@fecha_hoy>@fecha_vencimiento)begin   
 update CLIENTE_SUSCRIPCION set vigencia=0 where idCliSus=@idCliSus;  
 print 'suscripcion vencida';  
 end  
 else begin  
 select*from CRONOGRAMA where idCS=@idCliSus;   
 end  
end




/*CREACION DE SP_INS_CLIENTE_SUSCRIPCION*/
CREATE procedure SP_INS_CLIENTE_SUSCRIPCION(  
@dni_cliente int,  
@idSuscripcion int,  
@idTrabajador int  
)  
as begin   
 -- CREAR NUEVA SUSCRIPCION PARA CLIENTE YA EXISTENTE  
  
 --Obtener idCliente mediante el dni  
 declare @idCliente int;  
 select @idCliente = idPersona from PERSONA where @dni_cliente=dni_persona;  
  
 declare @duracion int;   
 set @duracion= dbo.duracion(@idSuscripcion);  
  
 DECLARE @fecha_inicio DATE;  
 --SET @fecha_inicio = CONVERT(DATETIME, SWITCHOFFSET(GETDATE(), DATEPART(TZOFFSET, SYSDATETIMEOFFSET())));  
 SET @fecha_inicio = dbo.ObtenerFechaHoy();  
  
 declare @fecha_vencimiento date;  
 SET @fecha_vencimiento = DATEADD(MONTH, @duracion, @fecha_inicio);  
   
 insert into CLIENTE_SUSCRIPCION(idCliente,idSuscripcion,fecha_suscripcion,fecha_vencimiento,vigencia)  
 values(@idCliente,@idSuscripcion,@fecha_inicio,@fecha_vencimiento,1)  
   
 --Generar cronograma  
  
 declare @idCliSus int;   
 select @idCliSus=idCliSus from CLIENTE_SUSCRIPCION  where idCliente=@idCliente;  
   
 declare @iddursus int;  
 select @iddursus=duracion_sus from dbo.ClienteSusDur where idCliSus=@idCliSus;  
   
 declare @monto float;  
 select @monto=costo_sus from dbo.ClienteSusDur where idCliSus=@idCliSus;  
  
 declare @contador int=1;  
 while @contador<=@iddursus begin  
  if(@contador=1)begin  
  select @fecha_inicio=fecha_suscripcion from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  
  SET @fecha_vencimiento = DATEADD(MONTH, @contador, @fecha_inicio);  
  
  insert into CRONOGRAMA(idCS,fecha_vencimiento,monto_cuota,Estado_cuota,fecha_inicio)   
  values(@idCliSus,@fecha_vencimiento,@monto,'PENDIENTE',@fecha_inicio);  
  SET @contador = @contador + 1;   
  end    
  else begin   
  declare @fecha_suscripcion date;  
  select @fecha_suscripcion =fecha_suscripcion from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  select @fecha_inicio=fecha_suscripcion from CLIENTE_SUSCRIPCION where idCliSus=@idCliSus;  
  SET @fecha_inicio=DATEADD(MONTH,@contador-1, @fecha_inicio); --declare @fecha_inicio date;  
  set @fecha_vencimiento = DATEADD(MONTH,@contador, @fecha_suscripcion);--declare @fecha_vencimiento date;  
   
  insert into CRONOGRAMA(idCS,fecha_vencimiento,monto_cuota,Estado_cuota,fecha_inicio)   
  values(@idCliSus,@fecha_vencimiento,@monto,'PENDIENTE',@fecha_inicio);  
  SET @contador = @contador + 1;   
  end  
 end  
 --Insertar factura cab  
 declare @idFactura varchar(10);  
 declare @numero int;  
 select @numero = COUNT(*) from FACTURA_CAB;   
 set @numero = @numero + 1;  
 set @idFactura ='FA' + RIGHT('000'+CAST(@numero as varchar(3)),3);  
  
 DECLARE @fecha_fact DATE;  
 --SET @fecha_fact = CONVERT(DATETIME, SWITCHOFFSET(GETDATE(), DATEPART(TZOFFSET, SYSDATETIMEOFFSET())));  
 SET @fecha_fact = dbo.ObtenerFechaHoy();  
  
 insert into FACTURA_CAB(idFactura,idCliente,idTrabajador,fecha_fact,Estado_fac,IGV)  
 values(@idFactura,@idCliente,@idTrabajador,@fecha_fact,1,0.18)  
end





/*CREACION DE SP_MANTENIMIENTO_PRODUCTOS*/
CREATE PROCEDURE SP_MANTENIMIENTO_PRODUCTOS(  
@idProducto int,  
@descripcion_pro varchar(100),  
@preciou_pro float,  
@stock_pro int,  
@tipo int  
)  
as  
 if(@tipo=1)begin --insertar  
  insert into PRODUCTO (descripcion_pro,preciou_pro,stock_pro,Esatdo)  
  values(@descripcion_pro,@preciou_pro,@stock_pro,1);  
 end  
 else if(@tipo=2) begin --eliminar   
  if not exists (select * from PRODUCTO where idProducto=@idProducto)  
   select 'El producto ingresado NO existe'  
  else  
   delete Producto where idProducto=@idProducto;   
 end  
 else if(@tipo=3)begin --modificar  
  if not exists (select * from PRODUCTO where idProducto=@idProducto)  
   select 'El producto ingresado NO existe'  
  else  
   update Producto set descripcion_pro=@descripcion_pro,  
        preciou_pro=@preciou_pro,  
        stock_pro=@stock_pro,  
        Esatdo=1 where idProducto=@idProducto;   
 end  
 else if(@tipo=4) begin --buscar  
   select*from PRODUCTO where descripcion_pro like '%@descripcion_pro%';  
 end




/*CREACION DE SP_INS_DETALLE_FACTURA*/
 CREATE procedure SP_INS_DETALLE_FACTURA   
(  
@idFactura varchar(10),  
@tipo_concepto int,  
@idProducto int,  
@cantidad int  
)  
as begin  
  
 declare @idCli int;  
 select @idCli = idCliente from FACTURA_CAB where idFactura=@idFactura;  
  
 declare @idCliSus int;  
 select @idCliSus=idCliSus from CLIENTE_SUSCRIPCION where idCliente=@idCli;  
 declare @fecha_fact date;  
 declare @subTotal float;  
 select @fecha_fact=fecha_fact from FACTURA_CAB where idFactura=idFactura;  
 declare @idCuota int;  
 if(@tipo_concepto=1)begin  
 select @idProducto= idProducto from PRODUCTO where idProducto=@idProducto;    
 select @subTotal =preciou_pro from PRODUCTO where idProducto=@idProducto;  
 SET @subTotal=@cantidad*@subTotal;  
 end  
   
 else if(@tipo_concepto=2)begin  
 select @idCuota= idCuota from CRONOGRAMA where idCS=@idCliSus and @fecha_fact between fecha_inicio and fecha_vencimiento;  
 select @subtotal=monto_cuota from CRONOGRAMA where idCuota=@idCuota;  
 end  
  
 insert into DETALLE_FACTURA(idFactura,tipo_concepto,idProducto,idCuota,cantidad,subTotal)   
 values(@idFactura,@tipo_concepto,@idProducto,@idCuota,@cantidad,@subtotal)  
  
 UPDATE CRONOGRAMA set Estado_cuota='PAGADO' where idCuota=@idCuota;  
end





/*CREACION DE SP_INS_FACTURA_CAB*/
CREATE procedure SP_INS_FACTURA_CAB(    
@idCliente int,    
@idTrabajador int   
)    
as begin    
 declare @idFactura varchar(10);    
 declare @numero int;    
 select @numero = COUNT(*) from FACTURA_CAB;     
 set @numero = @numero + 1;    
 set @idFactura ='FA' + RIGHT('000'+CAST(@numero as varchar(3)),3);    
    
 DECLARE @fecha_fact DATE;    
 SET @fecha_fact = CONVERT(DATETIME, SWITCHOFFSET(GETDATE(), DATEPART(TZOFFSET, SYSDATETIMEOFFSET())));    
 SET @fecha_fact = CAST(GETDATE() AS DATE);    
    
 insert into FACTURA_CAB(idFactura,idCliente,idTrabajador,fecha_fact,Estado_fac,IGV)    
 values(@idFactura,@idCliente,@idTrabajador,@fecha_fact,1,0.18)    
end





/*CREACION DE SP_total*/
CREATE procedure total(  
@idFactura varchar(5)  
)  
  
as begin   
 SELECT f.idFactura,f.fecha_fact,p.nombre_persona,p.apellido_persona,c.ruc,  
 SUM(df.subTotal) AS TotalFactura, SUM(df.subTotal) * 1.0018 AS TotalFinal  
FROM FACTURA_CAB f  
INNER JOIN DETALLE_FACTURA df ON f.idFactura = df.idFactura  
inner join PERSONA p on p.idPersona=f.idCliente  
inner join CLIENTE c on p.idPersona=c.idPersona  
WHERE f.idFactura = @idFactura  
GROUP BY f.idFactura,nombre_persona,apellido_persona,f.fecha_fact,c.ruc;  
  
 end



/*CREACION DE SP_MANTENIMIENTO_SUSCRIPCION*/
 CREATE PROCEDURE SP_MANTENIMIENTO_SUSCRIPCION(
@idSuscripcion int,
@descripcion_sus varchar(20),
@duracion_sus int,
@costo_sus float,
@tipo int
)
as
	if(@tipo=1)begin --insertar
		insert into SUSCRIPCION(descripcion_sus,duracion_sus,costo_sus,Estado)
		values(@descripcion_sus,@duracion_sus,@costo_sus,1);
	end
	else if(@tipo=2) begin --eliminar 
		if not exists (select * from SUSCRIPCION where idSuscripcion=@idSuscripcion)
			select 'El tipo de suscripcion ingresada NO existe'
		else
			delete SUSCRIPCION where idSuscripcion=@idSuscripcion; 
	end
	else if(@tipo=3)begin --modificar
		if not exists (select * from SUSCRIPCION where idSuscripcion=@idSuscripcion)
			select 'El tipo de suscripcion ingresada NO existe'
		else
			update SUSCRIPCION set descripcion_sus=@descripcion_sus,
								duracion_sus=@duracion_sus,
								costo_sus=@costo_sus,
								Estado=1 where idSuscripcion=@idSuscripcion; 
	end
	else if(@tipo=4) begin --buscar
			select*from SUSCRIPCION where descripcion_sus like '%@descripcion_sus%';
	end



/*CREACION DE SP_INS_PERSONA*/
	CREATE procedure SP_INS_PERSONA(      
@idPersonaMo int,  
 @dni_cliente varchar(8) ='',    
 @nombre_cliente varchar(80) ='',    
 @apellido_cliente varchar(80) ='',    
 @telefono_cliente varchar(80)='',    
 @tipo_persona int,    
 @seguro_trabajador varchar(3),    
 @ruc varchar(20) ,  
 @tipo int  
 )    
 as begin    
 DECLARE @idPersona int  ;   
 if(@tipo=1) begin --insertar  
     
 insert into PERSONA(dni_persona,nombre_persona,apellido_persona,telefono_persona,tipo_persona)     
 values(@dni_cliente,@nombre_cliente,@apellido_cliente,@telefono_cliente,@tipo_persona)    
    
  SET @idPersona = SCOPE_IDENTITY()     
  SELECT @idPersona AS idPersona    
    
  if(@tipo_persona=1)begin    
   insert into Cliente(idPersona,RUC)     
   values(@idPersona,@ruc)    
   end    
    
  else if(@tipo_persona=2)begin    
   insert into TRABAJADOR(idPersona,seguro_trabajador)    
   values(@idPersona,@seguro_trabajador)    
   end  
 end  
 else if(@tipo=2)begin --modificar  
  --SELECT @idPersona = idPersona FROM PERSONA WHERE dni_persona = @dni_cliente;  
    
   update PERSONA    
   set dni_persona=@dni_cliente,     
    nombre_persona=@nombre_cliente,    
    apellido_persona=@apellido_cliente,    
    telefono_persona=@telefono_cliente,    
    tipo_persona=@tipo_persona     
   where idPersona=@idPersonaMo  ;  
   update TRABAJADOR     
   set seguro_trabajador=@seguro_trabajador     
   where idPersona=@idPersonaMo ;   
    
  update CLIENTE    
   set RUC=@ruc    
   where idPersona=@idPersonaMo;    
 END  
 else if(@tipo=3)begin --eliminar  
  delete from PERSONA where idPersona=@idPersonaMo;  
  delete from CLIENTE where idPersona=@idPersonaMo;  
  delete from TRABAJADOR where idPersona=@idPersonaMo;  
 end  
end



/*CREACION DE SP_UPD_CLIENTE_SUSCRIPCION*/
CREATE procedure SP_UPD_CLIENTE_SUSCRIPCION(    
@idCliSus int    
  
)    
as begin     
delete CLIENTE_SUSCRIPCION where idCliSus=@idCliSus  
delete CRONOGRAMA where idCS=@idCliSus  
end
