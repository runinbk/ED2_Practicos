-- Aquí pegue los programas desarrollados en SQL SERVER
 1.
create function Mytabla(@calm int) returns table as return
				( select cprd,
						sum(cant) as cantidad,
						avg(prec) as precio
					from sumi
					where calm=@calm
					group by cprd,
						cant) 2.
create trigger ActualizaDescuento on dventas
for
insert as declare @imdescp decimal(12,2),
	@importe decimal(12,2),
	@codproduc int,@nroventas int
select @nroventas=nvta
from inserted
select @importe=(sum(impt)),
	@codproduc=count(distinct(cprd))
from dventas
where nvta=@nroventas if(@codproduc>2)begin if(@importe>100)begin
				set @imdescp=@importe*0.1
				update pventas
				set ides=@imdescp
				where nvta=@nroventas end end 3.
				create trigger importet on dventas
				for
				insert as declare @nroventas int,@importet decimal(12,2)
				select @nroventas=nvta
				from inserted
				select @importet=(sum(impt))
				from dventas where nvta=@nroventas
				update pventas
				set itot=@importet where nvta=@nroventas
				create procedure PA_GPreventa as begin tran begin try declare @codprod int,@cantid decimal(12,2),
					@precio decimal(12,2),
					@ult int,@codalma int,@impt decimal(12,2)
				set @codalma=1
				select @ult=max(nvta)+1
				from pventas
				insert into pventas
				values(@ult,
												'Leo Messi',
												@codalma,
												getdate(),
												0,
												0) declare c
				cursor for
								(select *
									from dbo.Mytabla(@codalma)) open c fetch next
				from c into @codprod,
					@cantid,
					@precio while (@@FETCH_STATUS=0)begin print cast(@codprod as varchar)+cast(@cantid as varchar)+cast(@precio as varchar)
				set @cantid=@cantid*0.8
				set @impt=@cantid*@precio
				insert into dventas
				values(@ult,
												@codprod,
												@cantid,
												@precio,
												@impt) fetch next
				from c into @codprod,
					@cantid,
					@precio end close c deallocate c
				commit tran end try begin catch
				rollback tran end catch