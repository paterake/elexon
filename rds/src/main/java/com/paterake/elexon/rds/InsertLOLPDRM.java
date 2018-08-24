package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyLOLPDRM;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class InsertLOLPDRM extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.lolpdrm VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException {
        DataBodyLOLPDRM body = (DataBodyLOLPDRM) dataBody;
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setObject(++i, body.lolp12Forecast, Types.DECIMAL);
        pstmt.setObject(++i, body.drm12Forecast, Types.DECIMAL);
        pstmt.setObject(++i, body.lolp8HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.drm8HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.lolp4HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.drm4HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.lolp2HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.drm2HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.lolp1HourForecast, Types.DECIMAL);
        pstmt.setObject(++i, body.drm1HourForecast, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        //this uses a from date and to date
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.lolpdrm");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.lolpdrm:" + result + " rows");
        } finally {
            statement.close();
        }
    }
    public void postStep(Connection conn) {}

}
