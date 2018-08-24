CREATE TABLE bmrs.fuelinst (
  header_record_type                     VARCHAR(255)
, header_file_type                       VARCHAR(255)
, record_type                            VARCHAR(255)
, start_time_of_half_hr_period           DATE
, settlement_period                      INT
, publishing_period_commencing_time      TIMESTAMP WITHOUT TIME ZONE
, ccgt                                   INT
, oil                                    INT
, coal                                   INT
, nuclear                                INT
, wind                                   INT
, ps                                     INT
, npshyd                                 INT
, ocgt                                   INT
, other                                  INT
, intfr                                  INT
, intirl                                 INT
, intned                                 INT
, intew                                  INT
, biomass                                INT
, active_flag                            VARCHAR(255)
);