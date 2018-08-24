package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyWINDFORFUELHH;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertWINDFORFUELHH extends InsertTable{
    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn.prepareStatement("INSERT INTO bmrs.windforfuelhh VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyWINDFORFUELHH body = (DataBodyWINDFORFUELHH) dataBody;
        Timestamp timestamp;
        try {
             timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.initialForecastPublishingPeriodCommencingTime).getTime());
        } catch (Exception e){
            timestamp = null;
        }
        Timestamp timestamp2;
        try {
            timestamp2 = new java.sql.Timestamp(dateTimeFormat.parse(body.latestForecastPublishingPeriodCommencingTime).getTime());
        } catch (Exception e){
            timestamp2 = null;
        }
        Timestamp timestamp3;
        try {
            timestamp3 = new java.sql.Timestamp(dateTimeFormat.parse(body.outTurnPublishingPeriodCommencingTime).getTime());
        } catch (Exception e){
            timestamp3 = null;
        }

        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, dateConverter.convert(body.startTimeOfHalfHrPeriod));
        pstmt.setObject(++i, body.settlementPeriod,Types.INTEGER);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setObject(++i, body.initialForecastSpnGeneration.equalsIgnoreCase("NULL") ? null : body.initialForecastSpnGeneration, Types.INTEGER);
        pstmt.setTimestamp(++i, timestamp2);
        pstmt.setObject(++i, body.latestForecastSpnGeneration.equalsIgnoreCase("NULL") ? null : body.latestForecastSpnGeneration, Types.INTEGER);
        pstmt.setTimestamp(++i, timestamp3);
        pstmt.setObject(++i, body.fuelTypeGeneration.equalsIgnoreCase("") ? null : body.fuelTypeGeneration, Types.INTEGER);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.windforfuelhh");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.windforfuelhh:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) {}

}
