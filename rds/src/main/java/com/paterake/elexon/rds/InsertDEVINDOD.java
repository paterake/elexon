package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyDEVINDOD;
import com.paterake.elexon.model.DataHeader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class InsertDEVINDOD extends InsertTable{
    public void createPreparedStatement(Connection conn) throws SQLException {
        pstmt = conn
                .prepareStatement("INSERT INTO bmrs.devindod VALUES (?,?,?,?,?,?,?,?,?)");
    }


    public void setPreparedStatement(DataHeader header, DataBody dataBody) throws SQLException {
        DataBodyDEVINDOD body = (DataBodyDEVINDOD) dataBody;
        int i = 0;
        pstmt.setString(++i, header.recordType);
        pstmt.setString(++i, header.fileType);
        pstmt.setString(++i, body.recordType);
        pstmt.setDate(++i, java.sql.Date.valueOf(body.settlementDay));
        pstmt.setObject(++i,body.volume,Types.INTEGER);
        pstmt.setObject(++i, body.dailyEnergyVolumeNormalReference, Types.INTEGER);
        pstmt.setObject(++i, body.dailyEnergyVolumeLowReference, Types.INTEGER);
        pstmt.setObject(++i, body.dailyEnergyVolumeHighReference, Types.INTEGER);
        pstmt.setString(++i, body.activeFlag);
        pstmt.addBatch();
        rowCount++;
    }

    public void preStep(Connection conn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.devindod");
        try {
            int result = statement.executeUpdate();
            System.out.println("truncated: bmrs.devindod:" + result + " rows");
        } finally {
            statement.close();
        }
    }

    public void postStep(Connection conn) throws SQLException {

    }

}
