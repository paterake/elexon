package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyDEMMF2T14D;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertDEMMF2T14D extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.demmf2t14d VALUES (?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyDEMMF2T14D body = (DataBodyDEMMF2T14D) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());

        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, dateConverter.convert(body.dayOfForecast));
        pstmt.setObject(++i, body.startTimeOfHalfHrPeriod, Types.INTEGER);
        pstmt.setString(++i, body.boundaryID);
        pstmt.setTimestamp(++i, timestamp);
        pstmt.setObject(++i, body.demand, Types.INTEGER);
        pstmt.setObject(++i, body.margin, Types.INTEGER);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) {}
    public void postStep(Connection conn) {}

}
