package com.paterake.elexon.pipeline;

import com.paterake.elexon.ingest.Ingestion;
import com.paterake.elexon.ingest.calendar.SettlementCalendar;
import com.paterake.elexon.transform.Transformer;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.stream.IntStream;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

public class DataLoader {

  private String persistFile(boolean s3Ind, String reportName, String... argList)
      throws IOException {
    Ingestion ingest = new Ingestion();
    return ingest.ingestFile(s3Ind, reportName, argList);
  }

  private void writeToRDS(boolean s3Ind, String reportName, String fileName)
      throws ClassNotFoundException, SQLException, JAXBException, XMLStreamException, IOException, InstantiationException, IllegalAccessException, ParseException {
    Transformer transformer = new Transformer();
    if (s3Ind) {
      transformer.processS3Data(reportName, "bmrs", fileName);
    } else {
      transformer.processData(reportName, fileName);
    }
  }

  private void loadData(boolean s3Ind, String reportName, String... argList)
      throws IOException, ClassNotFoundException, SQLException, XMLStreamException, JAXBException, IllegalAccessException, InstantiationException, ParseException {
    String fileName = persistFile(s3Ind, reportName, argList);
    writeToRDS(s3Ind, reportName, fileName);
  }

  public void loadData(boolean s3Ind, String[] args)
      throws IllegalAccessException, ParseException, JAXBException, IOException, XMLStreamException, SQLException, InstantiationException, ClassNotFoundException {
    if (args.length == 0) {
      throw new UnsupportedOperationException("Usage:"
          + "\n   DataLoader <reportName>         [optional parameters]"
          + "\n   -- currently defined :"
          + "\n   DataLoader UOU2T14D"
          + "\n   DataLoader PHYBMDATA            [<settlementDate> <settlementPeriod>]"
          + "\n   DataLoader DETSYSPRICES         [<settlementDate> <settlementPeriod>]"
          + "\n   DataLoader FORDAYDEM            [<fromDate> <toDate>]"
          + "\n   DataLoader MessageListRetrieval [<fromDate> <toDate>]"
          + "");
    } else if (args.length == 1) {
      loadData(s3Ind, args[0]);
    } else {
      String[] argList = IntStream.range(1, args.length).mapToObj(i -> args[i])
          .toArray(String[]::new);
      loadData(s3Ind, args[0], argList);
    }
  }

  public void loadHistoricData(boolean s3Ind, String reportName) {

    Calendar now = Calendar.getInstance();
    Calendar currDate = (Calendar) now.clone();
    currDate.add(Calendar.DATE, -1);

    now.set(2018, 5, 5);
    currDate.set(2018, 5, 17);

    //now.set(Calendar.MONTH, 5);
    //now.set(Calendar.DAY_OF_MONTH, 1);
    now.add(Calendar.DATE, -1);
    now.set(Calendar.HOUR_OF_DAY, 22);
    now.set(Calendar.MINUTE, 32);
    now.set(Calendar.SECOND, 0);
    while (now.before(currDate)) {
      for (int i = 0; i < 48; i++) {
        now.add(Calendar.MINUTE, 30);
        SettlementCalendar cal = SettlementCalendar.getSettlementDate(now);
        System.out.println(cal.settlementDate + ":" + cal.settlementPeriod);
        try {
          loadData(s3Ind, reportName, cal.settlementDate, Integer.toString(cal.settlementPeriod));
        } catch (Exception e) {
          System.out.println("failed: " + cal.settlementDate + ":" + cal.settlementPeriod);
        }
      }
      now.set(Calendar.HOUR_OF_DAY, 22);
      now.set(Calendar.MINUTE, 32);
      now.set(Calendar.SECOND, 0);
    }
  }

  public static void main(String[] args) throws Exception {
    boolean s3Ind = true;
    DataLoader loader = new DataLoader();
    loader.loadData(s3Ind, args);
    //loader.loadData(s3Ind, "LOLPDRM");
    //loader.loadData(s3Ind, "SYSWARN", "2018-05-01", "2018-05-02");
    //loader.loadHistoricData(true, "PHYBMDATA");
  }
}
