package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyFORDAYDEM;
import com.paterake.elexon.model.DataHeader;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertFORDAYDEM extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    pstmt = conn.prepareStatement("INSERT INTO bmrs.fordaydem VALUES (?,?,?,?,?,?,?,?,?,?)");
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody)
      throws SQLException, ParseException {
    DataBodyFORDAYDEM body = (DataBodyFORDAYDEM) dataBody;
    Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());
    int i = 0;
    pstmt.setString(++i, header.recordType);
    pstmt.setString(++i, header.fileType);
    pstmt.setString(++i, body.recordType);
    pstmt.setString(++i, body.nationalBoundaryIdentifier);
    pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
    pstmt.setObject(++i, body.settlementPeriod, java.sql.Types.INTEGER);
    pstmt.setTime(++i, getSettlementPeriodTime(Integer.parseInt(body.settlementPeriod)));
    pstmt.setTimestamp(++i, timestamp);
    pstmt.setObject(++i, coalesce(body.demand, body.spnDemand, body.spnGeneration), java.sql.Types.INTEGER);
    pstmt.setString(++i, body.activeFlag);
    pstmt.addBatch();
    rowCount++;
  }

  public void preStep(Connection conn) throws SQLException {
    PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.fordaydem");
    try {
      int result = statement.executeUpdate();
      System.out.println("truncated: bmrs.fordaydem:" + result + " rows");
    } finally {
      statement.close();
    }
  }

  public void postStep(Connection conn) {
  }

}
