package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyDEMMF2T52W;
import com.paterake.elexon.model.DataHeader;
import com.paterake.elexon.rds.validation.Converter;

import java.sql.*;
import java.text.ParseException;

public class InsertDEMMF2T52W extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.demmf2t52w VALUES (?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody)
            throws SQLException, ParseException {
        DataBodyDEMMF2T52W body = (DataBodyDEMMF2T52W) dataBody;
        Timestamp timestamp = new java.sql.Timestamp(dateTimeFormat.parse(body.publishingPeriodCommencingTime).getTime());
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setObject(++i, body.calendarWeekNumber, Types.INTEGER);
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
