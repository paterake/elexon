package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyINDOITSDO;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertINDOITSDO extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.indoitsdo VALUES (?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyINDOITSDO body = (DataBodyINDOITSDO) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.startTimeOfHalfHrPeriod));
        pstmt.setString(++i, body.systemZone);
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setObject(++i, body.demand, Types.INTEGER);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        //this uses a from date and to date
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.indoitsdo");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.indoitsdo:" + result + " rows");
        } finally {
            statement.close();
        }
    }
    public void postStep(Connection conn) {}

}
