CREATE TABLE bmrs.syswarn (
        header_record_type          VARCHAR(255)
      , header_file_type            VARCHAR(255)
      , record_type                 VARCHAR(255)
      , warning_date_time           TIMESTAMP
      , warning_text                TEXT
      , active_flag                 VARCHAR(255)
      );