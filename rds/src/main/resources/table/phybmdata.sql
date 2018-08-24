CREATE TABLE bmrs.phybmdata (
  header_record_type          VARCHAR(255)
, header_file_type            VARCHAR(255)
, header_settlement_date      DATE
, header_settlement_period    INT
, record_type                 VARCHAR(255)
, bm_unit_id                  VARCHAR(255)
, bm_unit_type                VARCHAR(255)
, lead_party_name             VARCHAR(255)
, ngc_bm_unit_name            VARCHAR(255)
, settlement_date             DATE
, settlement_period           INT
, time_from                   TIMESTAMP WITHOUT TIME
, pn_level_from               DECIMAL
, time_to                     TIMESTAMP WITHOUT TIME
, pn_level_to                 DECIMAL
, active_flag                 VARCHAR(255)
);
