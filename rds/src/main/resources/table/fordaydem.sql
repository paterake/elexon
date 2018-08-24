CREATE TABLE bmrs.fordaydem (
  header_record_type          VARCHAR(255)
, header_file_type            VARCHAR(255)
, record_type                 VARCHAR(255)
, national_boundary_id        VARCHAR(255)
, settlement_date             DATE
, settlement_period           INT
, settlement_period_time      TIME WITHOUT TIME ZONE
, publ_period_commencing_time TIMESTAMP WITHOUT TIME ZONE
, value                       INT
, active_flag                 VARCHAR(255)
);
