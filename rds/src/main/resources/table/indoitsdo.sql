CREATE TABLE bmrs.indoitsdo(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    start_time_of_half_hr_period DATE,
    system_zone                 VARCHAR(255),
    settlement_period           INT,
    publ_period_commencing_time TIMESTAMP WITHOUT TIME ZONE,
    demand                      INT,
    active_flag                 VARCHAR(255)
);