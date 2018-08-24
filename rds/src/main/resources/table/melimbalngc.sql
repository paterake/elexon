CREATE TABLE bmrs.melimbalngc(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    settlement_date             DATE,
    settlement_period           INT,
    national_boundary_identifier VARCHAR(255),
    publ_period_commencing_time TIMESTAMP WITHOUT TIME ZONE,
    imbalance_value             INT,
    margin                      INT,
    active_flag                 VARCHAR(255)
);