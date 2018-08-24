package com.paterake.elexon.ingest.calendar;

import com.paterake.elexon.ingest.Ingestion;
import java.util.Calendar;

public class SettlementCalendar {
  public int settlementPeriod;
  public String settlementDate;

  public static SettlementCalendar getSettlementDate(Calendar entry) {
    Calendar now = (Calendar) entry.clone();
    SettlementCalendar settlementCalendar = new SettlementCalendar();
    settlementCalendar.settlementPeriod = CalendarUtil.getSettlementPeriod(now);
    if (settlementCalendar.settlementPeriod < 3) {
      now.add(Calendar.DATE, 1);
    }
    settlementCalendar.settlementDate = CalendarUtil.formattedDate(now);
    return settlementCalendar;
  }

  public static SettlementCalendar getSettlementDate() {
    return getSettlementDate(Calendar.getInstance());
  }

  public static SettlementCalendar getSettlementDate(int minOffset) {
    Calendar now = Calendar.getInstance();
    now.add(Calendar.MINUTE, minOffset);
    return getSettlementDate(now);
  }

  public static void main(String[] args) {
    SettlementCalendar cal = SettlementCalendar.getSettlementDate();
    System.out.println(cal.settlementDate + ":" + cal.settlementPeriod);
  }

}
