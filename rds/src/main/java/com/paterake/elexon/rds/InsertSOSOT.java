package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodySOSOT;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSOSOT extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.sosot VALUES (?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException {
        DataBodySOSOT body = (DataBodySOSOT) dataBody;
        int i = 0;

        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setTimestamp(++i, java.sql.Timestamp.valueOf(body.warningDateTime+ ":00"));
        pstmt.setString(++i, body.messageText);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.sosot");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.sosot:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }
}
