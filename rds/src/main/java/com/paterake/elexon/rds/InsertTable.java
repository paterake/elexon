package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataHeader;
import com.paterake.elexon.rds.validation.Converter;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

/**
 * @author Rakesh Patel
 * An abstract class that declares methods to insert values into a table.
 * Also includes methods to transform input data into suitable formats for the output table.
 */
public abstract class InsertTable {

  public static int rowCount = 0;

  static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


  /**
   * Converts a given settlement period into its time format. e.g 1 becomes 23.30, 2 becomes 00:00, ...
   * @param settlementPeriod The settlement period (1-48) that is to be converted into a time (23.30 - 23:00).
   * @return The time representation of the given settlement period.
   */
  public static Time getSettlementPeriodTime(int settlementPeriod) {
    Time time = java.sql.Time.valueOf("22:30:00");
    LocalTime ltime = time.toLocalTime();
    ltime = ltime.plusMinutes(30 * settlementPeriod);
    return Time.valueOf(ltime);
  }

  /**
   * Discovers which of some inputs is not null.
   * @param params Fields from an XML bound Databody object.
   * @param <T>
   * @return The value of the input that is not null.
   */
  public static <T> T coalesce(T... params) {
    for (T param : params) {
      if (param != null) {
        return param;
      }
    }
    return null;
  }

  public PreparedStatement pstmt;


  /**
   *Creates a PreparedStatment and assigns an INSERT statement with placeholders.
   * @param conn
   * @throws SQLException
   */
  public abstract void createPreparedStatement(Connection conn) throws SQLException;

  /**
   * Assigns values from a given DataHeader and DataBody to placeholders in an SQL statement.
   * @param header A DataHeader object filled with values from an XML header.
   * @param body A DataBody object filled with values from an XML item.
   * @throws SQLException
   * @throws ParseException
   */
  public abstract void setPreparedStatement(DataHeader header, DataBody body)
      throws SQLException, ParseException;

  /**
   * Defines any transaction to be done to the table before the main transaction.
   * @param conn The connection to the database.
   * @throws SQLException
   */
  public abstract void preStep(Connection conn) throws SQLException;

  /**
   * Defines any transaction to be done to the table after the main transaction.
   * @param conn The connection to the database.
   * @throws SQLException
   */
  public abstract void postStep(Connection conn) throws SQLException;

  /**
   * Converts a string to a sql date type
   * @return null or sql.Date type
   */
  public Converter<String, Date> dateConverter =
            s -> "NULL".equalsIgnoreCase(s) || "".equalsIgnoreCase(s) ? null : java.sql.Date.valueOf(s);
  /**
   * Converts a string to a sql time type
   * @return null or sql.Time type
   */
  public Converter<String, java.sql.Time> timeConverter =
          s -> "NULL".equalsIgnoreCase(s) || "".equalsIgnoreCase(s) ? null : java.sql.Time.valueOf(s);
}
