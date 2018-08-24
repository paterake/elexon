package com.paterake.elexon.rds;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

public class SettlementPeriodTimeTest {

  @Test
  public void testSettlementPeriod() {
    System.out.println(InsertTable.getSettlementPeriodTime(2));
  }

  @Test
  public void testZoneDateTime() throws ParseException {

    String dateTime = "2018-04-06 12:46:59Z";
    String defaultTimezone = TimeZone.getDefault().getID();
    Date date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ")).parse(dateTime.replaceAll("Z$", "+0000"));
    Timestamp timestamp = new java.sql.Timestamp(date.getTime());
    System.out.println(timestamp);

  }
}