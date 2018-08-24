CREATE TABLE bmrs.b1610 (
  document_type                         VARCHAR(255)
, business_type                         VARCHAR(255)
, process_type                          VARCHAR(255)
, time_series_id                        VARCHAR(255)
, quantity                              DECIMAL
, curve_type                            VARCHAR(255)
, resolution                            VARCHAR(255)
, settlement_date                       DATE
, settlement_period                     INT
, power_system_resource_type            VARCHAR(255)
, registered_resource_eic_code          VARCHAR(255)
, market_generation_unit_eic_code       VARCHAR(255)
, market_generation_bm_unit_id          VARCHAR(255)
, market_generation_ngc_bm_unit_id      VARCHAR(255)
, bm_unit_id                            VARCHAR(255)
, ngc_bm_unit_id                        VARCHAR(255)
, active_flag                           VARCHAR(255)
, document_id                           VARCHAR(255)
, document_rev_num                      VARCHAR(255)
);