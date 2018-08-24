package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyB1770;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class InsertB1770 extends InsertTable {

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.b1770 VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException {
        DataBodyB1770 body = (DataBodyB1770) dataBody;
        int i = 0;
        pstmt.setString(++i, body.timeSeriesID);
        pstmt.setString(++i, body.businessType);
        pstmt.setString(++i, body.controlArea);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setObject(++i, body.imbalancePriceAmountGBP, Types.DECIMAL);
        pstmt.setString(++i, body.priceCategory);
        pstmt.setString(++i, body.curveType);
        pstmt.setString(++i, body.resolution);
        pstmt.setString(++i, body.documentType);
        pstmt.setString(++i, body.processType);
        pstmt.setString(++i, body.activeFlag);
        pstmt.setString(++i, body.docStatus);
        pstmt.setString(++i, body.documentID);
        pstmt.setString(++i, body.documentRevNum);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {

    }

    public void postStep(Connection conn) throws SQLException {

    }
}
