use GCProduction
go

drop procedure dbo.GetConfigCodeInfo
go

create procedure dbo.GetConfigCodeInfo(@ConfigCode varchar(20))
as
begin
	set nocount on;
	
	select distinct PtSeries
		, PtGroupNo
		, PtControlDescription
		, PtPriceGridNo
		, PtPriceGridNo2
	from OePriceGroupConfig pgc, OePriceControlTable pct
	where PgGroupNo = PtGroupNo
		and PgSeries = PtSeries
		and PgConfigCode = @ConfigCode
end
go

--exec GetConfigCodeInfo @ConfigCode='DDD'
--select * from sys.messages where message_id = 102
