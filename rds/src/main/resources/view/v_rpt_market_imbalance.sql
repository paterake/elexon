CREATE OR REPLACE VIEW bmrs.v_rpt_market_imbalance
AS
select v.bm_unit_id
     , v.lead_party_name
     , v.fuel_type
     , v.static_value
     , v.settlement_date												                        settlement_date
     , v.settlement_period                                     			    settlement_period
     , COALESCE(SUM(data_value) filter (where record_type = 'PN') , 0)	pn_value
     , COALESCE(SUM(data_value) filter (where record_type = 'MEL'), 0)	mel_value
  from bmrs.v_rpt_phybmdata v
 group by
       v.bm_unit_id
     , v.lead_party_name
     , v.fuel_type
     , v.static_value
     , v.settlement_date
     , v.settlement_period
;
