CREATE TABLE bmrs.fuelinsthhcur (
header_record_type VARCHAR(255),
header_file_type VARCHAR(255),
record_type VARCHAR(255),
fuel_type   VARCHAR(255),
current_MW  int,
current_percentage DECIMAL,
last_half_hr_lcl_strt_time TIMESTAMP WITHOUT TIME ZONE,
last_half_hr_lcl_end_time TIMESTAMP WITHOUT TIME ZONE,
last_half_hr_mw int,
last_half_hr_pcent DECIMAL,
last_24_hr_lcl_strt_time TIMESTAMP WITHOUT TIME ZONE,
last_24_hr_lcl_end_time TIMESTAMP WITHOUT TIME ZONE,
last_24_hr_mwh int,
last_24_hr_pcent DECIMAL,
active_flag VARCHAR(255)
);