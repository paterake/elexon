package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyHISTSYSWARN;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertHISTSYSWARN extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.histsyswarn VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyHISTSYSWARN body = (DataBodyHISTSYSWARN) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.warningDateTime).getTime());

        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setString(++i, body.warningType);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setDate(++i, dateConverter.convert(body.effectiveFrom));
        pstmt.setTime(++i, timeConverter.convert(body.timesEffectiveFrom));
        pstmt.setTime(++i, timeConverter.convert(body.timesEffectiveTo));
        pstmt.setObject(++i, (body.shortfall.equals("") ? null : body.shortfall), Types.DECIMAL);//body.shortfall, Types.DECIMAL);
        pstmt.setDate(++i, dateConverter.convert(body.dateWarningCancelled));
        pstmt.setTime(++i, timeConverter.convert(body.timeWarningCancelled));
        pstmt.setString(++i, body.warningText);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws  SQLException{
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.histsyswarn");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.histsyswarn:" + result + " rows");
        } finally {
            statement.close();
        }
    }
    public void postStep(Connection conn) {}

}
