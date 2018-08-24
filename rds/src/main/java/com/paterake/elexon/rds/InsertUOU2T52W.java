package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyUOU2T52W;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertUOU2T52W extends InsertTable {

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn.prepareStatement("INSERT INTO bmrs.uou2t52w VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyUOU2T52W body = (DataBodyUOU2T52W)dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setString(++i, body.bmUnitName);
        pstmt.setString(++i, body.fuelType);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setString(++i, body.systemZone);
        pstmt.setObject(++i, body.calendarWeekNumber, Types.INTEGER);
        pstmt.setObject(++i, body.year, Types.INTEGER);
        pstmt.setObject(++i, body.outputUsable, java.sql.Types.INTEGER);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.uou2t52w");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.uou2t52w:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) {}

}

