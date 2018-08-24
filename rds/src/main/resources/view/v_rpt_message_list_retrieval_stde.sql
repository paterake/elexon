create or replace view bmrs.v_rpt_message_list_retrieval_stde
as
  with src_mlrl
    as (
         select *
           from bmrs.v_rpt_message_list_retrieval
       )
select stde.settlement_date
     , stde.settlement_period
     , stde.settlement_period_timestamp
     , mlrl.*
     , rank() over ( partition by
                               stde.settlement_date
                             , stde.settlement_period
                             , mlrl.asset_id
                         order by
                               mlrl.event_end - mlrl.event_start ASC
                   ) data_rnk
  from bmrs.v_rpt_settlement_date stde
       left outer join
       src_mlrl                     mlrl
         on stde.settlement_period_timestamp between mlrl.event_start and mlrl.event_end
 order by
       stde.settlement_date
     , stde.settlement_period
     , mlrl.asset_id
     , mlrl.event_start
     , mlrl.event_end
;