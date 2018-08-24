package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodySYSMSG;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

public class InsertSYSMSG extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.sysmsg VALUES (?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException, ParseException {
        DataBodySYSMSG body = (DataBodySYSMSG) dataBody;
        int i = 0;

        Timestamp timestamp = new Timestamp((dateTimeFormat.parse(body.messageDateTime + ":00").getTime()));
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setString(++i, body.messageType);
        pstmt.setString(++i, body.messageText);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.sysmsg");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.sysmsg:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }
}
