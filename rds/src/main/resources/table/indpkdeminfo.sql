CREATE TABLE bmrs.indpkdeminfo(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    start_of_half_hr_period     DATE,
    calendar_week_number        INT,
    demand                      DECIMAL,
    time_of_peak                TIME WITHOUT TIME ZONE
);