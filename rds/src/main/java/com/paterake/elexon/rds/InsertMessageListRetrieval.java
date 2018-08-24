package com.paterake.elexon.rds;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataBodyMessageListRetrieval;
import com.paterake.elexon.model.DataBodyPHYBMDATA;
import com.paterake.elexon.model.DataHeader;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class InsertMessageListRetrieval extends InsertTable {

  public void createPreparedStatement(Connection conn) throws SQLException {
    pstmt = conn
        .prepareStatement(
            "INSERT INTO bmrs.message_list_retrieval VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
  }

  public void setPreparedStatement(DataHeader header, DataBody dataBody)
      throws SQLException, ParseException {
    DataBodyMessageListRetrieval body = (DataBodyMessageListRetrieval) dataBody;

    Timestamp publishedTimestamp = new java.sql.Timestamp(
        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ"))
            .parse(body.publishedDateTime.replaceAll("Z$", "+0000")).getTime());
    Timestamp eventStartTimestamp = new java.sql.Timestamp(
        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ"))
            .parse(body.eventStart.replaceAll("Z$", "+0000")).getTime());
    Timestamp eventEndTimestamp = new java.sql.Timestamp(
        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ"))
            .parse(body.eventEnd.replaceAll("Z$", "+0000")).getTime());

    int i = 0;
    pstmt.setString(++i, body.messageID);
    pstmt.setObject(++i, body.sequenceID, Types.INTEGER);
    pstmt.setString(++i, body.messageHeading);
    pstmt.setString(++i, body.eventType);
    pstmt.setTimestamp(++i, publishedTimestamp);
    pstmt.setString(++i, body.participant_MarketParticipantID);
    pstmt.setString(++i, body.assetID);
    pstmt.setString(++i, body.assetEICCode);
    pstmt.setString(++i, body.assetType);
    pstmt.setString(++i, body.affectedUnit);
    pstmt.setString(++i, body.affectedArea);
    pstmt.setString(++i, body.assetFuelType);
    pstmt.setObject(++i, body.assetNormalCapacity, Types.DECIMAL);
    pstmt.setObject(++i, body.availableCapacity, Types.DECIMAL);
    pstmt.setTimestamp(++i, eventStartTimestamp);
    pstmt.setTimestamp(++i, eventEndTimestamp);
    pstmt.setString(++i, body.durationUncertainty);
    pstmt.setString(++i, body.cause);
    pstmt.setString(++i, body.eventStatus);
    pstmt.setString(++i, body.relatedInformation);
    pstmt.setString(++i, body.activeFlag);
    pstmt.setObject(++i, body.revisionNumber, Types.INTEGER);
    pstmt.setString(++i, body.messageType);
    pstmt.setString(++i, body.unavailabilityType);
    pstmt.setObject(++i, body.unavailableCapacity, Types.DECIMAL);
    pstmt.addBatch();
    rowCount++;
  }

  public void preStep(Connection conn) throws SQLException {
    PreparedStatement statement = conn.prepareStatement("TRUNCATE bmrs.message_list_retrieval");
    try {
      int result = statement.executeUpdate();
      System.out.println("truncated: bmrs.message_list_retrieval:" + result + " rows");
    } finally {
      statement.close();
    }
  }

  public void postStep(Connection conn) {
  }

}
