create or replace view bmrs.v_rpt_message_list_retrieval
as
         select *
           from bmrs.message_list_retrieval mlrl
          where mlrl.active_flag    = 'Y'
            and mlrl.event_status   = 'Active'
            and mlrl.asset_fuel_type is not null
            and mlrl.asset_id       = 'T_SHBA-1'
;
