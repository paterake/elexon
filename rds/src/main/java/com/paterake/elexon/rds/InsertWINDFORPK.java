package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyWINDFORPK;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertWINDFORPK extends InsertTable {

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.windforpk VALUES (?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyWINDFORPK body = (DataBodyWINDFORPK) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.dataLastUpdated.concat(":00")).getTime());

        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.dayAndDate));
        pstmt.setTime(++i, timeConverter.convert(body.startTimeOfHalfHrPeriod.concat(":00")));
        pstmt.setObject(++i, body.peakMaxGeneration, Types.INTEGER);
        pstmt.setObject(++i, body.totalMeteredCapacity, Types.INTEGER);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        //this uses a from date and to date
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.windforpk");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.windforpk:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) {}
}
