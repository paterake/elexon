package com.paterake.elexon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a JSON item as a Java Object. These items will come from 'data_pipeline.json' and will represent the structure/parameters of a specific report.
 */
public class ElexonReport {

  @JsonProperty("reportName")
  public String reportName;

  @JsonProperty("modelClass")
  public String modelClass;

  @JsonProperty("databaseClass")
  public String databaseClass;

  @JsonProperty("defaultFromDateOffset")
  public int defaultFromDateOffset;

  @JsonProperty("defaultToDateOffset")
  public int defaultToDateOffset;

  @JsonProperty("defaultPeriodOffset")
  public int defaultPeriodOffset;

  @JsonProperty("searchParameterList")
  public List<String> searchParameterList;

  @JsonProperty("fixedParameterList")
  public List<String> fixedParameterList;

  public String getReportName() {
    return reportName;
  }

  public String getModelClass() {
    return modelClass;
  }

  public String getDatabaseClass() {
    return databaseClass;
  }

  public int getDefaultFromDateOffset() { return defaultFromDateOffset;}

  public List<String> getSearchParameterList() {
    return searchParameterList;
  }

  public List<String> getFixedParameterList() {
    return fixedParameterList;
  }
}
