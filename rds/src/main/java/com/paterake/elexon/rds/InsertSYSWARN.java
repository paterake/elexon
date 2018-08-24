package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodySYSWARN;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertSYSWARN extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.syswarn VALUES (?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException, ParseException {
        DataBodySYSWARN body = (DataBodySYSWARN) dataBody;
        int i = 0;


        Timestamp timestamp = new Timestamp((dateTimeFormat.parse(body.warningDateTime + ":00").getTime()));
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setString(++i, body.warningText);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.syswarn");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.syswarn:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }
}
