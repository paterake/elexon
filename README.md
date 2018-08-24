#ELEXON Data Ingest

The code for ingesting Elexon data into S3 and RDS are summarised as follows:

1. definition of the elexon host details : cfg\src\main\resources\elexon.properties:  
Contains the generic parts of an api request.  
'host'    =  Host address: This is the first portion of the URI, and identifies the internet address of the BMRS.  
'suffix'  =  The name of the service being requested.  
'version' =  Version number: The version of the API being called.  
'apikey'  =  The unique authentication code granted to the users via the ELEXON Portal, giving them rights and permissions to use the API.

2. definition of report configuration in the file : cfg\src\main\resources\data_pipeline.json  
The following is the basic structure for adding a new report to this file:  
        
            {  
              "reportName": "REPORTNAME",  
              "modelClass": "DataBodyREPORTNAME",  
              "databaseClass": "InsertREPORTNAME"  
            }
Additional attributes can be added with a comma separator. The additional attributes can be found in the data_pipeline.schema.json file.  
The following is an example for the FORDAYDEM report:  

            {  
			  "reportName": "FORDAYDEM",  
			  "modelClass": "DataBodyFORDAYDEM",    
			  "databaseClass": "InsertFORDAYDEM",    
			  "defaultToDateOffset": 2,    
			  "searchParameterList": [    
			    "FromDate",    
			    "ToDate"    
			  ],    
			  "fixedParameterList": [    
			    "ZoneIdentifier=N"    
			  ]    
		    }  
            
3. definition of the report scheduling in the file : extract\src\main\resources\crontab   
The crontab file holds all of the cron jobs for each report. These are scheduled jobs for when each report needs to be accessed. A report can be accessed multiple times a day using one cron job. The structure of a cron job is as follows:
          
            * * * * * command
Each * represents a division of time in the following order from left to right: minute, hour, day of month, month, day of week. To have a job run at multiple times, separate the two numbers with a comma and no space. The following is an example of how to structure this:  
		    
            32 7 * * 1,3,5 java -cp pipeline-1.0-SNAPSHOT.jar com.paterake.elexon.pipeline.DataLoader REPORTNAME
This job will execute at 7:32am every Monday, Wednesday and Friday. To add a new report, simply replace REPORTNAME with the name of the report.

4. definition of the XML Bind Object definition under : model\src\main\java\com\paterake\elexon\model

5. Naming convention of the XML Bind Object : DataBody<ReportName>

6. definition of the Postgres RDS SQL Table Create statement: rds\src\main\resources\table:  
   The SQL statements to create tables with the correct column types for each report are found here. Before running the code, ensure that these statements  
   have been run and the tables are available for insertion.

7. definition of the JDBC insert statement that maps the XML Bind Object to the RDS Table:  rds\src\main\java\com\paterake\elexon\rds

8. Naming convention of the JDBC Insert class: Insert<ReportName>

