package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyDERBMDATA;
import com.paterake.elexon.model.DataHeader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class InsertDERBMDATA extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    StringBuilder sb = new StringBuilder("INSERT INTO bmrs.derbmdata VALUES (?");
    sb.append(new String(new char[39]).replace("\0", ",?"));
    sb.append(")");
    pstmt = conn.prepareStatement(sb.toString());
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException {
    DataBodyDERBMDATA body = (DataBodyDERBMDATA) dataBody;
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
    pstmt.setString(++i, body.dataType);
    pstmt.setString(++i, body.acceptanceID);
    pstmt.setString(++i, body.shortAcceptanceFlag);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPair1, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPair2, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPair3, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPair4, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPair5, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPair6, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPairMinus1, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPairMinus2, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPairMinus3, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPairMinus4, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPairMinus5, Types.DECIMAL);
    pstmt.setObject(++i, body.volumeAcceptedforBidOfferPairMinus6, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPair1, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPair2, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPair3, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPair4, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPair5, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPair6, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPairMinus1, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPairMinus2, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPairMinus3, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPairMinus4, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPairMinus5, Types.DECIMAL);
    pstmt.setObject(++i, body.cashFlowforBidOfferPairMinus6, Types.DECIMAL);
    pstmt.setObject(++i, body.total, Types.DECIMAL);
    pstmt.setString(++i, body.activeFlag);
    pstmt.addBatch();
    rowCount++;
  }

  public void preStep(Connection conn) throws SQLException {
  }

  public void postStep(Connection conn) throws SQLException {
  }

}
