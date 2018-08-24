CREATE TABLE bmrs.freq (
header_record_type   VARCHAR(255),
header_file_type     VARCHAR(255),
record_type          VARCHAR(255),
report_snapshot_time DATE,
spot_time            TIME WITHOUT TIME ZONE,
frequency            DECIMAL,
active_flag          VARCHAR(255)
);