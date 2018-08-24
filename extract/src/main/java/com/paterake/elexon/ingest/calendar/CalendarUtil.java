package com.paterake.elexon.ingest.calendar;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class CalendarUtil {

  static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

  public static int getSettlementPeriod(LocalDateTime entry) {
    LocalDateTime offsetDateTime = entry.minusHours(23);
    int hr = offsetDateTime.getHour();
    int mi = offsetDateTime.getMinute();
    int offset = ((hr % 24) + 1) * 2 - 1 + (mi / 30);
    return offset;
  }

  public static int getSettlementPeriod(Calendar entry) {
    return getSettlementPeriod(
        LocalDateTime.ofInstant(entry.toInstant(), entry.getTimeZone().toZoneId()));
  }

  public static String formattedDate(Calendar entry) {
    return sdfDate.format(entry.getTime());
  }

  public static String formattedDateTime() {
    DateTimeFormatter dateFormatString = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
    DateTimeFormatter timeFormatString = DateTimeFormatter.ofPattern("HHmmss", Locale.ENGLISH);
    LocalDateTime ldt = LocalDateTime.now();
    String dateString = dateFormatString.format(ldt);
    String timeString = timeFormatString.format(ldt);
    return dateString + "-" + timeString;
  }

  public static void main(String[] args) {
    System.out.println(CalendarUtil.getSettlementPeriod(Calendar.getInstance()));
    System.out.println(CalendarUtil.formattedDate(Calendar.getInstance()));
  }
}
