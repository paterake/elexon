CREATE TABLE bmrs.windforpk(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    day_and_date                DATE,
    start_time_of_half_hr_period TIME WITHOUT TIME ZONE,
    peak_max_generation         INT,
    total_metered_capacity      INT,
    data_last_updated           TIMESTAMP WITHOUT TIME ZONE,
    active_flag                 VARCHAR(255)
);