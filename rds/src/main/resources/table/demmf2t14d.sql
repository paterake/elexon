CREATE TABLE bmrs.demmf2t14d(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    day_of_forecast             DATE,
    start_time_of_half_hr_prd   INT,
    boundary_id                 VARCHAR(255),
    publishing_prd_commencing_time TIMESTAMP WITHOUT TIME ZONE,
    demand                      INT,
    margin                      INT,
    active_flag                 VARCHAR(255)
);