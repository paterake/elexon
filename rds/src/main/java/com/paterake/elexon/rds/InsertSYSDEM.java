package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodySYSDEM;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;

public class InsertSYSDEM extends InsertTable {
    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.sysdem VALUES (?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException {
        DataBodySYSDEM body = (DataBodySYSDEM) dataBody;
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.startTimeOfHalfHrPeriod));
        pstmt.setObject(++i, body.settlementPeriod, Types.INTEGER);
        pstmt.setObject(++i, body.demand, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        //this uses a from date and to date
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.sysdem");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.sysdem:" + result + " rows");
        } finally {
            statement.close();
        }
    }
    public void postStep(Connection conn) {}


}
