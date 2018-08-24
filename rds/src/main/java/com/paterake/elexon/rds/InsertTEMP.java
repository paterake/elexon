package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyTEMP;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

public class InsertTEMP extends InsertTable {

    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.temp VALUES (?,?,?,?,?,?,?,?,?)");
    }

    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException {
        DataBodyTEMP body = (DataBodyTEMP)dataBody;
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.publishingPeriodCommencingTime));
        pstmt.setObject(++i, body.temperature, Types.DECIMAL);
        pstmt.setObject(++i, body.normalReferenceTemperature, Types.DECIMAL);
        pstmt.setObject(++i, body.lowReferenceTemperature, Types.DECIMAL);
        pstmt.setObject(++i, body.highReferenceTemperature, Types.DECIMAL);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.temp");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.temp:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }
}
