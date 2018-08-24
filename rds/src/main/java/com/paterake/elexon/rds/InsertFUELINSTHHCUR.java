package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyFUELINSTHHCUR;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;

public class InsertFUELINSTHHCUR extends InsertTable{
    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.FUELINSTHHCUR VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }


    public void setPreparedStatement(DataHeader header, DataBody dataBody)
        throws SQLException, ParseException {
        DataBodyFUELINSTHHCUR body = (DataBodyFUELINSTHHCUR) dataBody;

        Timestamp lastHalfHourLocalStartTs = new java.sql.Timestamp(dateTimeFormat.parse(body.lastHalfHourLocalStartTime + ":00").getTime());
        Timestamp lastHalfHourLocalEndTs = new java.sql.Timestamp(dateTimeFormat.parse(body.lastHalfHourLocalEndTime + ":00").getTime());
        Timestamp last24HourLocalStartTs = new java.sql.Timestamp(dateTimeFormat.parse(body.last24HourLocalStartTime + ":00").getTime());
        Timestamp last24HourLocalEndTs = new java.sql.Timestamp(dateTimeFormat.parse(body.last24HourLocalEndTime + ":00").getTime());

        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setString(++i, body.fuelType);
        pstmt.setObject(++i,body.currentMW,Types.INTEGER);
        pstmt.setObject(++i, body.currentPercentage, Types.DECIMAL);
        pstmt.setTimestamp(++i, lastHalfHourLocalStartTs);
        pstmt.setTimestamp(++i, lastHalfHourLocalEndTs);
        pstmt.setObject(++i, body.lastHalfHourMW, Types.INTEGER);
        pstmt.setObject(++i, body.lastHalfHourPercentage, Types.DECIMAL);
        pstmt.setTimestamp(++i, last24HourLocalStartTs);
        pstmt.setTimestamp(++i, last24HourLocalEndTs);
        pstmt.setObject(++i, body.last24HourMWh, Types.INTEGER);
        pstmt.setObject(++i, body.last24HourPercentage, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.FUELINSTHHCUR");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.FUELINSTHHCUR:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }





}
