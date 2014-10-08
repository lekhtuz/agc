use GCProduction
go

if exists (select 1 from sys.views where name='v_configcodeinfo')
begin
	drop view v_configcodeinfo
end
go

create view v_configcodeinfo as
	select distinct PgConfigCode
		, PtSeries
		, PtGroupNo
		, PtControlDescription
		, PtPriceGridNo
		, PtPriceGridNo2
	from OePriceGroupConfig pgc
		, OePriceControlTable pct
	where PgGroupNo = PtGroupNo
		and PgSeries = PtSeries;
go

--select * from sys.messages where message_id = 102
