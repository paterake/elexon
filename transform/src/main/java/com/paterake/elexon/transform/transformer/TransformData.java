package com.paterake.elexon.transform.transformer;

import com.paterake.elexon.model.DataBody;
import com.paterake.elexon.model.DataHeader;
import com.paterake.elexon.rds.InsertTable;
import com.paterake.elexon.rds.RDSConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class TransformData {

  final int BATCH_SIZE = 1000;

  private void moveToTag(XMLStreamReader reader, String tagName) throws XMLStreamException {
    while (reader.hasNext() && (!reader.isStartElement() || !reader.getLocalName()
        .equals(tagName))) {
      reader.next();
    }
    reader.next();
  }

  private <T extends DataBody> void bindDataRds(XMLStreamReader reader, Unmarshaller unmarshaller,
      Class<T> dataClass, DataHeader header, InsertTable insertCmd)
      throws XMLStreamException, JAXBException, SQLException, ParseException {
    int i = 0;
    while (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
      JAXBElement<T> data = unmarshaller.unmarshal(reader, dataClass);
      insertCmd.setPreparedStatement(header, data.getValue());
      if (i % BATCH_SIZE == BATCH_SIZE - 1) {
        try {
          insertCmd.pstmt.executeBatch();
        } catch (SQLException e) {
          try {
            throw e.getNextException();
          } catch (Exception e2) {
            throw e;
          }
        }
        insertCmd.pstmt.clearBatch();
        insertCmd.pstmt.getConnection().commit();
      }
      if (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
        reader.next();
      }
      i++;
    }
    if (i % BATCH_SIZE != 0) {
      try {
        insertCmd.pstmt.executeBatch();
      } catch (SQLException e) {
        try {
          throw e.getNextException();
        } catch (Exception e2) {
          throw e;
        }

      }
      insertCmd.pstmt.clearBatch();
      insertCmd.pstmt.getConnection().commit();
    }
  }

  private <T extends DataBody> void bindData(XMLStreamReader reader, Unmarshaller unmarshaller,
      Class<T> dataClass, List<T> clcnData) throws XMLStreamException, JAXBException {
    while (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
      JAXBElement<T> data = unmarshaller.unmarshal(reader, dataClass);
      clcnData.add(data.getValue());
      if (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
        reader.next();
      }
    }
  }

  public <T extends DataBody> void getBody(DataHeader header, Class<T> dataClass,
      Class rdsClass, String systemId, InputStream inputStream)
      throws XMLStreamException, JAXBException, IOException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException {

    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(systemId, inputStream);

    JAXBContext ucontext = JAXBContext.newInstance(dataClass);
    Unmarshaller unmarshaller = ucontext.createUnmarshaller();

    RDSConnection rdsConn = new RDSConnection();
    Connection conn = rdsConn.getConnection();
    InsertTable insertCmd = (InsertTable) rdsClass.newInstance();
    insertCmd.createPreparedStatement(conn);

    insertCmd.preStep(conn);
    boolean readInd = true;
    while (readInd) {
      try {
        moveToTag(reader, "responseList");
        bindDataRds(reader, unmarshaller, dataClass, header, insertCmd);
      } catch (NoSuchElementException e) {
        readInd = false;
      }
    }

    System.out.println("Inserted " + insertCmd.rowCount + " rows into " + systemId);
    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(
          "INSERT INTO bmrs.app_log VALUES('" + systemId + "', " + insertCmd.rowCount + ")");
      conn.commit();
    }

    insertCmd.postStep(conn);
    reader.close();
    inputStream.close();
    conn.close();

  }

  public <T extends DataBody> List<T> parseBody(Class<T> dataClass, String systemId,
      InputStream inputStream)
      throws XMLStreamException, JAXBException, IOException {
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(systemId, inputStream);
    List<T> clcnData = new ArrayList<T>();
    JAXBContext ucontext = JAXBContext.newInstance(dataClass);
    Unmarshaller unmarshaller = ucontext.createUnmarshaller();
    boolean readInd = true;
    while (readInd) {
      try {
        moveToTag(reader, "responseList");
        bindData(reader, unmarshaller, dataClass, clcnData);
      } catch (NoSuchElementException e) {
        readInd = false;
      }
    }
    reader.close();
    inputStream.close();
    return clcnData;
  }

  public static void main(String[] args)
      throws ClassNotFoundException, IOException, JAXBException, XMLStreamException {
    TransformData transformData = new TransformData();
    String reportName = "DERBMDATA";
    String fileName = "c:/temp/DERBMDATA/DERBMDATA-2018-07-05-25.xml";
    Class dataClass = Class.forName("com.paterake.elexon.model.DataBody" + reportName);
    List clcnData = transformData
        .parseBody(dataClass, reportName, new FileInputStream(new File(fileName)));
    System.out.println(clcnData.size());
  }

}



