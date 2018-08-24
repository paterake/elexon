CREATE TABLE bmrs.pkdemyesttdytom(
    header_record_type      VARCHAR(255),
    header_file_type        VARCHAR(255),
    record_type             VARCHAR(255),
    date_                   DATE,
    tsdf_demand             INT,
    tsdf_start_time_of_hlf_hr_prd  TIME WITHOUT TIME ZONE,
    itsdo_demand            INT,
    itsdo_start_time_of_hlf_hr_prd TIME WITHOUT TIME ZONE,
    publishing_prd_commencing_time TIMESTAMP WITHOUT TIME ZONE,
    active_flag             VARCHAR(255)
);