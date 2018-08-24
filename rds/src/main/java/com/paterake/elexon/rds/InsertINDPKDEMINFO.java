package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyINDPKDEMINFO;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;

public class InsertINDPKDEMINFO extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    pstmt = conn.prepareStatement("INSERT INTO bmrs.indpkdeminfo VALUES (?,?,?,?,?,?,?)");
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody)
      throws SQLException {
    DataBodyINDPKDEMINFO body = (DataBodyINDPKDEMINFO) dataBody;
    int i = 0;
    pstmt.setString(++i, header.recordType);
    pstmt.setString(++i, header.fileType);
    pstmt.setString(++i, body.recordType);
    pstmt.setDate(++i, dateConverter.convert(body.startTimeOfHalfHrPeriod));
    pstmt.setObject(++i, body.calendarWeekNumber, java.sql.Types.INTEGER);
    pstmt.setObject(++i, body.demand, Types.DECIMAL);
    pstmt.setTime(++i, timeConverter.convert(body.timeOfPeak));
    pstmt.addBatch();
    rowCount++;
  }

  public void preStep(Connection conn) {
  }

  public void postStep(Connection conn) {
  }

}
