create or replace view bmrs.v_rpt_settlement_date
 as
 WITH src_settlement_period
    AS (
         SELECT current_date settlement_date
              , gs.n settlement_period
              , current_date - 1
              + make_time(22,30,00) + (gs.n*30 || ' minutes')::interval settlement_period_timestamp
           FROM generate_series(1,48) gs(n)
       )
     , src_day_offset
    as (
         select gs.day_increment from generate_series(0,13) gs(day_increment)
       )
select stpd.settlement_date + day_increment  settlement_date
     , stpd.settlement_period
     , stpd.settlement_period_timestamp + (dyos.day_increment || ' days')::interval  settlement_period_timestamp
     , dyos.day_increment
  from src_settlement_period           stpd
       cross join
       src_day_offset                  dyos
;
