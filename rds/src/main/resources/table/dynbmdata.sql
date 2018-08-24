CREATE TABLE bmrs.dynbmdata (
  header_record_type                    VARCHAR(255)
, header_file_type                      VARCHAR(255)
, header_settlement_date                VARCHAR(255)
, header_settlement_period              INT
, record_type                           VARCHAR(255)
, bm_unit_id                            VARCHAR(255)
, bm_unit_type                          VARCHAR(255)
, lead_party_name                       VARCHAR(255)
, ngc_bm_unit_name                      VARCHAR(255)
, settlement_date                       DATE
, settlement_period                     INT
, effective_time                        TIMESTAMP WITHOUT TIME ZONE
, run_up_rate_1                         DECIMAL
, run_up_elbow_2                        INT
, run_up_rate_2                         DECIMAL
, run_up_elbow_3                        INT
, run_up_rate_3                         DECIMAL
, run_down_rate_1                       DECIMAL
, run_down_elbow_2                      INT
, run_down_rate_2                       DECIMAL
, run_down_elbow_3                      INT
, run_down_rate_3                       DECIMAL
, notice_to_deviate_from_zero           DECIMAL
, notice_to_deliver_bids                DECIMAL
, notice_to_deliver_offers              DECIMAL
, minimum_zero_time                     DECIMAL
, minimum_non_zero_time                 DECIMAL
, stable_export_limit                   DECIMAL
, stable_import_limit                   DECIMAL
, maximum_delivery_volume               DECIMAL
, maximum_delivery_period               DECIMAL
, active_flag                           VARCHAR(255)
);