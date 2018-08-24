CREATE TABLE bmrs.sosop (
  header_record_type          VARCHAR(255)
, header_file_type            VARCHAR(255)
, record_type                 VARCHAR(255)
, trade_type                  VARCHAR(255)
, start_time                  TIME
, settlement_date             DATE
, trade_direction             VARCHAR(255)
, contract_identification     VARCHAR(255)
, trade_quantity              INT
, trade_price                 DECIMAL
, active_flag                 VARCHAR(255)
);