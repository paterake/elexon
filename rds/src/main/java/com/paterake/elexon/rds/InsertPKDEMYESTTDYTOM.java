package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyPKDEMYESTTDYTOM;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertPKDEMYESTTDYTOM extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.pkdemyesttdytom VALUES (?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyPKDEMYESTTDYTOM body = (DataBodyPKDEMYESTTDYTOM) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime.concat(":00")).getTime());
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.date));
        pstmt.setObject(++i, body.tsdfDemand, Types.INTEGER);
        pstmt.setTime(++i, timeConverter.convert(body.tsdfStartTimeOfHalfHrPeriod.concat(":00")));
        pstmt.setObject(++i, body.itsdoDemand, Types.INTEGER);
        try{
            pstmt.setTime(++i, timeConverter.convert(body.itsdoStartTimeOfHalfHrPeriod.concat(":00")));
        } catch (Exception e){
            pstmt.setTime(i, null);
        }
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) {}
    public void postStep(Connection conn) {}

}
