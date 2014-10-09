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

if exists (select 1 from sys.views where name='v_pricegridmodel')
begin
	drop view v_pricegridmodel
end
go

create view v_pricegridmodel as
	select PdSeries
		,PdPriceGridNo
		,PdGlassType
		,PdHeight
		,PdWidth
		,PdWidth2
		,PdHeaderRequired
		,PdPrice
		, c.CmField01 as GlassDescription
		, c.CmField02 as GlassCode
	FROM OePriceGridModel pgd
		, Common c
	where c.CmKey = 'Glass_1'
	and PdGlassType = c.CmTinyInt;
go

--select * from sys.messages where message_id = 102
