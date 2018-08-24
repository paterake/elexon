package com.paterake.elexon.ingest;

import java.lang.invoke.MethodHandles;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ingestion {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  public ElexonCfg elexonCfg;

  public Ingestion() throws IOException {
    elexonCfg = new ElexonCfg();
  }

  public String ingestFile(boolean s3Ind, String reportName, String... argList) throws IOException {
    PullDataLoader loader = new PullDataLoader();
    String[] uriList = elexonCfg.constructURI(reportName, argList);
    String outputFile = loader.retrieveReport(s3Ind, reportName, uriList[0], uriList[1]);
    LOGGER.info(reportName + ":" + outputFile);
    return outputFile;
  }

  public static void main(String[] args) throws Exception {
    Ingestion ingest = new Ingestion();
    ingest.ingestFile(true, "INDPKDEMINFO"); //, "2018-06-28", "37");
  }
}
