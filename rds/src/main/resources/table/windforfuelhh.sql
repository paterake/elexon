CREATE TABLE bmrs.windforfuelhh (
  header_record_type                                      VARCHAR(255)
, header_file_type                                        VARCHAR(255)
, record_type                                             VARCHAR(255)
, start_time_of_half_hr_period                            DATE
, settlement_period                                       INT
, initial_forecast_publishing_period_commencing_time      TIMESTAMP WITHOUT TIME ZONE
, initial_forecast_spn_generation                         INT
, latest_forecast_publishing_period_commencing_time       TIMESTAMP WITHOUT TIME ZONE
, latest_forecast_spn_generation                          INT
, out_turn_publishing_period_commencing_time              TIMESTAMP WITHOUT TIME ZONE
, fuel_type_generation                                    INT
, active_flag                                             VARCHAR(255)
);
