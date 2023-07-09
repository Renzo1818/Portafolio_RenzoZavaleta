/*CREACION DE TR_DETALLE_FACTURA*/
CREATE TRIGGER ingreso on DETALLE_FACTURA
for insert
as begin
	declare @idP int;
	declare @unidad int;
	declare @stock int;
	declare @tipo_concepto int;

	select @unidad= cantidad from inserted;
	select @idP=idProducto from inserted;
	select @tipo_concepto=tipo_concepto from inserted;

	set @stock=(select stock_pro from PRODUCTO where idProducto=@idP);

	IF (@tipo_concepto = 2)
    BEGIN
        print 'no';
    END
	else begin
	update PRODUCTO set stock_pro=stock_pro-@unidad where idProducto=@idP;
	end    
END


/*CREACION DE TR_ContadorSuscripciones*/
create TRIGGER ContadorSuscripciones --contar cuantas veces se escoge cada tipo de suscripcion
ON CLIENTE_SUSCRIPCION
AFTER INSERT
AS
BEGIN
    DECLARE @idSuscripcion int;
    SET @idSuscripcion = (SELECT idSuscripcion FROM inserted);
	if(@idSuscripcion=3)begin
    IF EXISTS (SELECT 1 FROM Suscripciones WHERE idSuscripcion = @idSuscripcion)
    BEGIN
        UPDATE Suscripciones
        SET CantidadInserciones = CantidadInserciones + 1
        WHERE idSuscripcion = @idSuscripcion;
    END
    ELSE
    BEGIN
        INSERT INTO Suscripciones(idSuscripcion, CantidadInserciones)
        VALUES (@idSuscripcion, 1);
    END
	end
	else if(@idSuscripcion=2)begin
	IF EXISTS (SELECT 1 FROM Suscripciones WHERE idSuscripcion = @idSuscripcion)
    BEGIN
        UPDATE Suscripciones
        SET CantidadInserciones = CantidadInserciones + 1
        WHERE idSuscripcion = @idSuscripcion;
    END
    ELSE
    BEGIN
        INSERT INTO Suscripciones(idSuscripcion, CantidadInserciones)
        VALUES (@idSuscripcion, 1);
    END
	end
	else if(@idSuscripcion=1)begin
	IF EXISTS (SELECT 1 FROM Suscripciones WHERE idSuscripcion = @idSuscripcion)
    BEGIN
        UPDATE Suscripciones
        SET CantidadInserciones = CantidadInserciones + 1
        WHERE idSuscripcion = @idSuscripcion;
    END
    ELSE
    BEGIN
        INSERT INTO Suscripciones(idSuscripcion, CantidadInserciones)
        VALUES (@idSuscripcion, 1);
    END
	end
END


/*CREACION DE TR_productosAI*/
create trigger productosAI 
on producto after insert 
as begin 
	declare @idProducto INT;
	SELECT @idProducto=idProducto from inserted; 

	declare @descripcion_pro varchar(30);
	select @descripcion_pro =descripcion_pro from inserted;

	declare @precio float;
	select @precio=preciou_pro from inserted;

	declare @insertado datetime;
	set @insertado=GETDATE();

	insert into reg_productos(idProducto,descripcion_pro,precio,insertado)
	values(@idProducto,@descripcion_pro,@precio,@insertado)
end


/*CREACION DE TR_facturacabecera_insert*/
CREATE TRIGGER tr_facturacabecera_insert
ON FACTURA_CAB
AFTER INSERT
AS
BEGIN
    DECLARE @trabajador_nombre varchar(100);
    DECLARE @trabajador_id int;
	declare @idFactura varchar(5);

    -- Obtener el nombre y ID del trabajador relacionado con el registro insertado
	select @trabajador_id = idTrabajador from FACTURA_CAB
	SELECT @trabajador_nombre =nombre_trabajador from LISTA_TRABAJADORES where idTrabajado=@trabajador_id
	select @idFactura=idFactura from FACTURA_CAB
    WHERE idTrabajador IN (SELECT idTrabajador FROM inserted);

    -- Insertar el nombre y ID del trabajador en una tabla de registros de auditoría o cualquier otra tabla necesaria
    INSERT INTO REGREGISTRO_TRABAJADOR_FACTURA(id_Factura, id_Trabajador,nombre_trabajador)
    VALUES (@idFactura, @trabajador_id,@trabajador_nombre);
END;
