package com.paterake.elexon.ingest;

import com.paterake.elexon.ingest.calendar.CalendarUtil;
import com.paterake.elexon.ingest.calendar.SettlementCalendar;
import com.paterake.elexon.model.ElexonReport;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ElexonCfg {

  Properties appProps;
  Map<String, ElexonReport> elexonReportMap;

  public ElexonCfg() throws IOException {
    setAppProps();
    setElexonReport();
  }

  public void setAppProps() throws IOException {
    InputStream in = getClass().getClassLoader().getResourceAsStream("elexon.properties");
    appProps = new Properties();
    appProps.load(in);
  }

  public void setElexonReport() throws IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream("data_pipeline.json");
    TypeReference<List<ElexonReport>> mapType = new TypeReference<List<ElexonReport>>() {
    };

    ObjectMapper objectMapper = new ObjectMapper();
    List<ElexonReport> elexonReportList = objectMapper.readValue(is, TypeFactory.defaultInstance()
        .constructCollectionType(List.class, ElexonReport.class));

    elexonReportMap = elexonReportList.stream()
        .collect(Collectors.toMap(x -> x.reportName, x -> x));
  }

  public Properties getAppProps() {
    return appProps;
  }

  public Map<String, ElexonReport> getElexonReportMap() {
    return elexonReportMap;
  }

  public String formatString(String format, String... argList) {
    if (argList != null && argList.length > 0) {
      return String.format(format, argList);
    } else {
      return format;
    }
  }

  public String generateFileName(String reportName, String... argList) {
    StringBuilder sb = new StringBuilder();
    sb.append(reportName);
    if (argList != null && argList.length > 0) {
      Arrays.stream(argList).forEach(x -> sb.append("-" + x));
    } else {
      sb.append("-" + CalendarUtil.formattedDateTime());
    }
    sb.append("." + appProps.getProperty("serviceType").toLowerCase());
    return sb.toString();
  }

  public String[] constructURI(String reportName, String... argList) {
    ElexonReport report = elexonReportMap.get(reportName);
    StringBuilder sb = new StringBuilder();
    sb.append("https://");
    sb.append(appProps.getProperty("host"));
    sb.append("/" + appProps.getProperty("suffix"));
    sb.append("/" + reportName);
    sb.append("/" + appProps.getProperty("version"));
    sb.append("?ServiceType=" + appProps.getProperty("serviceType"));
    sb.append("&APIKey=" + appProps.getProperty("apiKey"));
    if (report.fixedParameterList != null && report.fixedParameterList.size() > 0) {
      report.fixedParameterList.stream().forEach(x -> sb.append("&" + x));
    }
    if (report.searchParameterList != null && report.searchParameterList.size() > 0) {
      report.searchParameterList.stream().forEach(x -> sb.append("&" + x + "=%s"));
    }
    String[] derivedArgList = null;
    if (report.searchParameterList == null || report.searchParameterList.size() == 0) {
      derivedArgList = null;
    } else if (argList.length > 0) {
      derivedArgList = argList;
    } else if (report.searchParameterList.contains("FromDate")
        || report.searchParameterList.contains("FromSettlementDate")
        || report.searchParameterList.contains("EventStart")) {
      LocalDateTime fromDate = LocalDateTime.now().plusDays(report.defaultFromDateOffset);
      LocalDateTime toDate = LocalDateTime.now().plusDays(report.defaultToDateOffset);
      DateTimeFormatter dateFormatString = DateTimeFormatter
          .ofPattern("yyyy-MM-dd", Locale.ENGLISH);
      derivedArgList = new String[]{dateFormatString.format(fromDate),
          dateFormatString.format(toDate)};
    } else if (report.searchParameterList.contains("SettlementDate")) {
      SettlementCalendar cal;
      if (report.defaultPeriodOffset == 0) {
        cal = SettlementCalendar.getSettlementDate();
      } else {
        cal = SettlementCalendar.getSettlementDate(30 * report.defaultPeriodOffset);
      }
      derivedArgList = new String[]{cal.settlementDate, Integer.toString(cal.settlementPeriod)};
    }
    return new String[]{formatString(sb.toString(), derivedArgList),
        generateFileName(reportName, derivedArgList)};
  }


  public static void main(String[] args) throws IOException {
    ElexonCfg elexonCfg = new ElexonCfg();

    Arrays.stream(elexonCfg.constructURI("MKTDEPTHDATA")).forEach(System.out::println);

    Arrays.stream(elexonCfg.constructURI("UOU2T14D")).forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("PHYBMDATA")).forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("DETSYSPRICES")).forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("FORDAYDEM")).forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("MessageListRetrieval")).forEach(System.out::println);

    Arrays.stream(elexonCfg.constructURI("PHYBMDATA", "2018-07-01", "48"))
        .forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("DETSYSPRICES", "2018-07-01", "48"))
        .forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("FORDAYDEM", "2018-06-30", "2018-07-02"))
        .forEach(System.out::println);
    Arrays.stream(elexonCfg.constructURI("MessageListRetrieval", "2018-06-30", "2018-07-02"))
        .forEach(System.out::println);


  }

}
