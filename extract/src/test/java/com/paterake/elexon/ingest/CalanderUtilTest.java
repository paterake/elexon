package com.paterake.elexon.ingest;

import com.paterake.elexon.ingest.calendar.CalendarUtil;
import com.paterake.elexon.ingest.calendar.SettlementCalendar;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class CalanderUtilTest {

  public static SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm" );

  @Test
  public void testSettlementPeriod() {
    Calendar now = Calendar.getInstance();
    now.set(Calendar.HOUR_OF_DAY, 22);
    now.set(Calendar.MINUTE, 32);
    now.set(Calendar.SECOND, 0);
    for (int i = 0; i < 48; i++) {
      now.add(Calendar.MINUTE, 30);
      SettlementCalendar settlementCalendar = SettlementCalendar.getSettlementDate(now);
      System.out.println(sdf.format(now.getTime()) + ":::" + settlementCalendar.settlementDate + ":" + settlementCalendar.settlementPeriod);
      Assert.assertEquals("Settlement Period mismatch", i + 1, settlementCalendar.settlementPeriod);
    }

    SettlementCalendar settlementCalendar = SettlementCalendar.getSettlementDate();
    System.out.println("Current:" + settlementCalendar.settlementDate + ":" + settlementCalendar.settlementPeriod);

  }

}
