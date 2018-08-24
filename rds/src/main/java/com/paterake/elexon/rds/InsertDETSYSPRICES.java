package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyDETSYSPRICES;
import com.paterake.elexon.model.DataBodyFORDAYDEM;
import com.paterake.elexon.model.DataHeader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;

public class InsertDETSYSPRICES extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    pstmt = conn.prepareStatement(
        "INSERT INTO bmrs.detsysprices VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody)
      throws SQLException {
    DataBodyDETSYSPRICES body = (DataBodyDETSYSPRICES) dataBody;
    if ("BID".equalsIgnoreCase(body.recordType) || "OFFER".equalsIgnoreCase(body.recordType)) {
      int i = 0;
      pstmt.setString(++i, header.recordType);
      pstmt.setString(++i, header.fileType);
      pstmt.setString(++i, body.recordType);
      pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
      pstmt.setObject(++i, body.settlementPeriod, java.sql.Types.INTEGER);
      pstmt.setTime(++i, getSettlementPeriodTime(Integer.parseInt(body.settlementPeriod)));
      pstmt.setObject(++i, body.index, java.sql.Types.INTEGER);
      pstmt.setString(++i, body.id);
      pstmt.setString(++i, body.acceptanceId);
      pstmt.setString(++i, body.bidOfferPairId);
      pstmt.setString(++i, body.cadlFlag);
      pstmt.setString(++i, body.soFlag);
      pstmt.setString(++i, body.storFlag);
      pstmt.setString(++i, body.repricedIndicator);
      pstmt.setObject(++i,
          "NULL".equalsIgnoreCase(body.reserveScarcityPrice) ? null : body.reserveScarcityPrice,
          Types.DECIMAL);
      pstmt.setObject(++i, coalesce(body.bidPrice, body.offerPrice), Types.DECIMAL);
      pstmt.setObject(++i, coalesce(body.bidVolume, body.offerVolume), Types.DECIMAL);
      pstmt.setObject(++i, body.dmatAdjustedVolume, Types.DECIMAL);
      pstmt.setObject(++i, body.arbitrageAdjustedVolume, Types.DECIMAL);
      pstmt.setObject(++i, body.nivAdjustedVolume, Types.DECIMAL);
      pstmt.setObject(++i, body.parAdjustedvolume, Types.DECIMAL);
      pstmt.setObject(++i, body.finalPrice, Types.DECIMAL);
      pstmt.setObject(++i, body.transmissionLossMultiplier, Types.DECIMAL);
      pstmt.setObject(++i, body.tlmAdjustedVolume, Types.DECIMAL);
      pstmt.setObject(++i, body.tlmAdjustedCost, Types.DECIMAL);
      pstmt.setObject(++i, body.totalOfTlmAdjustedVolume, Types.DECIMAL);
      pstmt.setObject(++i, body.totalOfTlmAdjustedCost, Types.DECIMAL);
      pstmt.addBatch();
      rowCount++;
    }
  }

  public void preStep(Connection conn) {
  }

  public void postStep(Connection conn) {
  }

}
