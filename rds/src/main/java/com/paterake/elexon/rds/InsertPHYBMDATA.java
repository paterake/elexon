package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyPHYBMDATA;
import com.paterake.elexon.model.DataHeader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;

public class InsertPHYBMDATA extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    pstmt = conn
        .prepareStatement("INSERT INTO bmrs.phybmdata VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody)
      throws SQLException, ParseException {
    DataBodyPHYBMDATA body = (DataBodyPHYBMDATA) dataBody;
    if ("MEL".equalsIgnoreCase(body.recordType) || "PN".equalsIgnoreCase(body.recordType)) {
      Timestamp timeFromTs = new java.sql.Timestamp(dateTimeFormat.parse(body.timeFrom).getTime());
      Timestamp timeToTs = new java.sql.Timestamp(dateTimeFormat.parse(body.timeTo).getTime());
      int i = 0;
      pstmt.setString(++i, header.recordType);
      pstmt.setString(++i, header.fileType);
      pstmt.setDate(++i, java.sql.Date.valueOf(header.settlementDate));
      pstmt.setObject(++i, header.settlementPeriod, java.sql.Types.INTEGER);
      pstmt.setString(++i, body.recordType);
      pstmt.setString(++i, body.bmUnitID);
      pstmt.setString(++i, body.bMUnitType);
      pstmt.setString(++i, body.leadPartyName);
      pstmt.setString(++i, body.ngcBMUnitName);
      pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
      pstmt.setObject(++i, body.settlementPeriod, java.sql.Types.INTEGER);
      pstmt.setTimestamp(++i, timeFromTs);
      if ("MEL".equalsIgnoreCase(body.recordType)) {
        pstmt.setObject(++i, body.melLevelFrom, Types.DECIMAL);
      } else {
        pstmt.setObject(++i, body.pnLevelFrom, Types.DECIMAL);
      }
      pstmt.setTimestamp(++i, timeToTs);
      if ("MEL".equalsIgnoreCase(body.recordType)) {
        pstmt.setObject(++i, body.melLevelTo, Types.DECIMAL);
      } else {
        pstmt.setObject(++i, body.pnLevelTo, Types.DECIMAL);
      }
      pstmt.setString(++i, body.activeFlag);
      pstmt.addBatch();
      rowCount++;
    }
  }

  public void preStep(Connection conn) {
  }

  public void postStep(Connection conn) {
  }

}
