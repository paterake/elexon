create or replace view bmrs.v_rpt_phybmdata as
with src_uou2t14d
  as (
     select distinct fuel_type, bm_unit_name from bmrs.uou2t14d
     )
select p.bm_unit_id
     , p.lead_party_name
     , s.fuel_type
     , rs.static_value
     , p.settlement_date
     , p.settlement_period
     , p.record_type
     , (p.pn_level_from + p.pn_level_to) / 2 	data_value
  from src_uou2t14d s
       inner join
       bmrs.phybmdata p
         on p.bm_unit_id = s.bm_unit_name
       inner join
       bmrs.ref_static   rs
         on rs.static_code = substring(p.bm_unit_id, 1, 1)
 where p.record_type in ('PN', 'MEL')
;
