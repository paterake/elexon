package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyMELIMBALNGC;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertMELIMBALNGC extends InsertTable {
    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.melimbalngc VALUES (?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyMELIMBALNGC body = (DataBodyMELIMBALNGC) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDate));
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setString(++i, body.nationalBoundaryIdentifier);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setObject(++i, body.imbalanceValue, Types.INTEGER);
        pstmt.setObject(++i, body.margin, Types.INTEGER);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        //this uses a from date and to date
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.melimbalngc");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.melimbalngc:" + result + " rows");
        } finally {
            statement.close();
        }
    }
    public void postStep(Connection conn) {}


}
