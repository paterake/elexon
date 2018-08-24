CREATE TABLE bmrs.app_log (
  report_name   VARCHAR(255)
, row_count     INT
, create_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);
