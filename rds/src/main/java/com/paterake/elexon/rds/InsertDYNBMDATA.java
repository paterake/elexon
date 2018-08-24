package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyDYNBMDATA;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;

public class InsertDYNBMDATA extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.dynbmdata VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException, ParseException {
        DataBodyDYNBMDATA body = (DataBodyDYNBMDATA) dataBody;
        int i = 0;

        Timestamp timestamp = new Timestamp((dateTimeFormat.parse(body.effectiveTime + ":00").getTime()));
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, header.settlementDate);
        pstmt.setObject(++i, header.settlementPeriod, Types.INTEGER);
        pstmt.setString(++i, body.recordType);
        pstmt.setString(++i, body.bmUnitID);
        pstmt.setString(++i, body.bMUnitType);
        pstmt.setString(++i, body.leadPartyName);
        pstmt.setString(++i, body.ngcBMUnitName);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setObject(++i, body.runUpRate1, Types.DECIMAL);
        pstmt.setObject(++i, body.runUpElbow2, Types.INTEGER);
        pstmt.setObject(++i, body.runUpRate2, Types.DECIMAL);
        pstmt.setObject(++i, body.runUpElbow3, Types.INTEGER);
        pstmt.setObject(++i, body.runUpRate3, Types.DECIMAL);
        pstmt.setObject(++i, body.runDownRate1, Types.DECIMAL);
        pstmt.setObject(++i, body.runDownElbow2, Types.INTEGER);
        pstmt.setObject(++i, body.runDownRate2, Types.DECIMAL);
        pstmt.setObject(++i, body.runDownElbow3, Types.INTEGER);
        pstmt.setObject(++i, body.runDownRate3, Types.DECIMAL);
        pstmt.setObject(++i, body.noticeToDeviateFromZero, Types.DECIMAL);
        pstmt.setObject(++i, body.noticeToDeliverBids, Types.DECIMAL);
        pstmt.setObject(++i, body.noticeToDeliverOffers, Types.DECIMAL);
        pstmt.setObject(++i, body.minimumZeroTime, Types.DECIMAL);
        pstmt.setObject(++i, body.minimumNonZeroTime, Types.DECIMAL);
        pstmt.setObject(++i, body.stableExportLimit, Types.DECIMAL);
        pstmt.setObject(++i, body.stableImportLimit, Types.DECIMAL);
        pstmt.setObject(++i, body.maximumDeliveryVolume, Types.DECIMAL);
        pstmt.setObject(++i, body.maximumDeliveryPeriod, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {

    }

    public void postStep(Connection conn) throws SQLException {

    }
}
