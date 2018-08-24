package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyMKTDEPTHDATA;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;

public class InsertMKTDEPTHDATA extends InsertTable {

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.mktdepthdata VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException {
        DataBodyMKTDEPTHDATA body = (DataBodyMKTDEPTHDATA) dataBody;

        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setObject(++i, body.imbalanceValue, Types.DECIMAL);
        pstmt.setObject(++i, body.totalOfferVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalBidVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalAcceptedOfferVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalAcceptedBidVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalUnpricedAccceptedOfferVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalUnpricedAcceptedBidVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalPricedAcceptedOfferVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.totalPricedAcceptedBidVolume, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) {}

    public void postStep(Connection conn) {}
}
