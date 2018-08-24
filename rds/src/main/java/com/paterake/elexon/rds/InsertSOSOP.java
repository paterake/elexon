package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodySOSOP;
import com.paterake.elexon.model.DataHeader;

import java.sql.*;
import java.text.ParseException;

public class InsertSOSOP extends InsertTable{

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.sosop VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException, ParseException {
        DataBodySOSOP body = (DataBodySOSOP) dataBody;
        int i = 0;

        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setString(++i, body.tradeType);
        pstmt.setTime(++i, java.sql.Time.valueOf(body.startTime));
        pstmt.setDate(++i, java.sql.Date.valueOf(body.date));
        pstmt.setString(++i, body.tradeDirection);
        pstmt.setString(++i, body.contractIdentification);
        pstmt.setObject(++i, body.tradeQuantity, Types.INTEGER);
        pstmt.setObject(++i, body.tradePrice, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {

    }

    public void postStep(Connection conn) throws SQLException {

    }
}
