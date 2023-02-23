--1.
-- DROP FUNCTION MyTabla

CREATE FUNCTION MyTabla(@calm SMALLINT) RETURNS TABLE AS RETURN
  (SELECT cprd,
          sum(cant) cant_exist,
          avg(prec) prec_prom
   FROM sumi
   WHERE calm=@calm
   GROUP BY cprd)--2.
--DROP TRIGGER ActualizaDescuento

CREATE TRIGGER ActualizaDescuento ON dventas AFTER
INSERT AS BEGIN DECLARE @impt_venta DECIMAL(12,2) DECLARE @nvta INT,@prod_difer INT
SELECT @nvta=nvta
FROM inserted
SELECT @prod_difer=ISNULL(COUNT(distinct cprd),0)
FROM dventas
WHERE nvta=@nvta
  SELECT @impt_venta=ISNULL(SUM(impt),0)
  FROM dventas WHERE nvta=@nvta IF (@prod_difer>2
                                    AND @impt_venta>100)
  UPDATE pventas
  SET ides = ides + @impt_venta*0.1 WHERE nvta = @nvta END --3.
-- DROP PROCEDURE PA_GPreventa

CREATE PROCEDURE PA_GPreventa AS DECLARE @cant_exist DECIMAL(12,
                                                             2),@impt DECIMAL(12,
                                                                              2),@prec_prom DECIMAL(12,
                                                                                                    2) DECLARE @cprd INT,@nvta INT,@calm INT DECLARE @nomb CHAR(25)
SET @nvta=
  (SELECT ISNULL(MAX(nvta),0)+1
   FROM pventas)
SET @calm=1
SET @nomb='Leo Messi' BEGIN TRY BEGIN TRAN -- Insertar una nueva fila en la tabla pventas

INSERT INTO pventas
VALUES (@nvta,
        @nomb,
        @calm,
        GETDATE(),
        0,
        0) -- Cursor para recorrer los datos de MyTabla e insertar el detalle de la preventa
 DECLARE c_tabla
CURSOR
FOR
SELECT cprd,
       cant_exist,
       prec_prom
FROM MyTabla(@calm) OPEN c_tabla FETCH NEXT
FROM c_tabla INTO @cprd,
                  @cant_exist,
                  @prec_prom WHILE @@FETCH_STATUS = 0 BEGIN -- Calcular el 80% de la cantidad existente

SET @cant_exist=@cant_exist*0.80 -- Calular el importe

SET @impt = @cant_exist * @prec_prom -- Insertar el detalle de la preventa

INSERT INTO dventas
VALUES(@nvta,
       @cprd,
       @cant_exist,
       @prec_prom,
       @impt) FETCH NEXT
FROM c_tabla INTO @cprd,
                  @cant_exist,
                  @prec_prom END CLOSE c_tabla DEALLOCATE c_tabla
COMMIT TRAN END TRY BEGIN CATCH RAISERROR('Viola la reglas de la BD', 16, 1, '...') CLOSE c_tabla DEALLOCATE c_tabla
ROLLBACK TRAN END CATCH -- Probar con estas instrucciones

delete
from dventas
delete
from pventas EXECUTE PA_GPreventa
select *
from MyTabla(1)
select *
from dventas
select *
from pventas