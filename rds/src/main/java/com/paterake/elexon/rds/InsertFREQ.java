package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyFREQ;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

public class InsertFREQ extends InsertTable {
    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.freq VALUES (?,?,?,?,?,?,?)");
    }


    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException {
        DataBodyFREQ body = (DataBodyFREQ)dataBody;
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.reportSnapshotTime));
        pstmt.setObject(++i,java.sql.Time.valueOf(body.spotTime));
        pstmt.setObject(++i, body.frequency, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.freq");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.freq:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }

}
