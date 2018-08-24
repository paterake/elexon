package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyFUELINST;
import com.paterake.elexon.model.DataHeader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;

public class InsertFUELINST extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    StringBuilder sb = new StringBuilder("INSERT INTO bmrs.fuelinst VALUES (?");
    sb.append(new String(new char[20]).replace("\0", ",?"));
    sb.append(")");
    pstmt = conn.prepareStatement(sb.toString());
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody)
      throws SQLException, ParseException {
    DataBodyFUELINST body = (DataBodyFUELINST) dataBody;

    Timestamp publishingPeriodCommencingTs = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());

    int i = 0;
    pstmt.setString(++i, header.recordType);
    pstmt.setString(++i, header.fileType);
    pstmt.setString(++i, body.recordType);
    pstmt.setDate(++i, java.sql.Date.valueOf(body.startTimeOfHalfHrPeriod));
    pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
    pstmt.setTimestamp(++i, publishingPeriodCommencingTs);
    pstmt.setObject(++i, body.ccgt, Types.INTEGER);
    pstmt.setObject(++i, body.oil, Types.INTEGER);
    pstmt.setObject(++i, body.coal, Types.INTEGER);
    pstmt.setObject(++i, body.nuclear, Types.INTEGER);
    pstmt.setObject(++i, body.wind, Types.INTEGER);
    pstmt.setObject(++i, body.ps, Types.INTEGER);
    pstmt.setObject(++i, body.npshyd, Types.INTEGER);
    pstmt.setObject(++i, body.ocgt, Types.INTEGER);
    pstmt.setObject(++i, body.other, Types.INTEGER);
    pstmt.setObject(++i, body.intfr, Types.INTEGER);
    pstmt.setObject(++i, body.intirl, Types.INTEGER);
    pstmt.setObject(++i, body.intned, Types.INTEGER);
    pstmt.setObject(++i, body.intew, Types.INTEGER);
    pstmt.setObject(++i, body.biomass, Types.INTEGER);
    pstmt.setString(++i, body.activeFlag);
    pstmt.addBatch();
    rowCount++;
  }

  public void preStep(Connection conn) throws SQLException {
    PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.fuelinst");
    try {
      int result = statement.executeUpdate();
      System.out.println("truncated: bmrs.fuelinst:" + result + " rows");
    } finally {
      statement.close();
    }
  }

  public void postStep(Connection conn) throws SQLException {

  }
}
