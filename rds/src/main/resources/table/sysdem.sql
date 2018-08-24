CREATE TABLE bmrs.sysdem(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    half_hr_period_start_time   DATE,
    settlement_period           INT,
    demand                      DECIMAL,
    active_flag                 VARCHAR(255)
);