package com.paterake.elexon.transform;

import com.paterake.elexon.ingest.s3.S3Util;
import com.paterake.elexon.transform.transformer.TransformData;
import com.paterake.elexon.transform.transformer.TransformHeader;
import com.paterake.elexon.model.DataHeader;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Transformer {

  public void processS3Data(String reportName, String bucketName, String fileName, Class dataClass, Class rdsClass)
      throws IOException, JAXBException, XMLStreamException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

    File scratchFile = File.createTempFile("prefix", "suffix");
    Files.copy(S3Util.getObject(S3Util.getS3Client(), bucketName, fileName), scratchFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    TransformHeader transformHeader = new TransformHeader();
    DataHeader header = transformHeader
        .getHeader(reportName, new FileInputStream(scratchFile));

    if (header != null) {
      System.out.println("ExtractedHeader: " + header.toString());
    }

    TransformData transformData = new TransformData();
    transformData.getBody(header, dataClass, rdsClass, reportName, new FileInputStream(scratchFile));

  }

  public void processData(String reportName, String fileName, Class dataClass, Class rdsClass)
      throws IOException, JAXBException, XMLStreamException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

    TransformHeader transformHeader = new TransformHeader();
    DataHeader header = transformHeader
        .getHeader(reportName, new FileInputStream(new File(fileName)));
    if (header != null) {
      System.out.println("ExtractedHeader: " + header.toString());
    }

    TransformData transformData = new TransformData();
    transformData.getBody(header, dataClass, rdsClass, reportName, new FileInputStream(new File(fileName)));

  }

  public void processS3Data(String reportName, String bucketName, String fileName)
      throws ClassNotFoundException, SQLException, JAXBException, XMLStreamException, IOException, IllegalAccessException, InstantiationException, ParseException {
    Class dataClass = Class.forName("com.paterake.elexon.model.DataBody" + reportName);
    Class rdsClass = Class.forName("com.paterake.elexon.rds.Insert" + reportName);
    processS3Data(reportName, bucketName, fileName, dataClass, rdsClass);
  }

  public void processData(String reportName, String fileName)
      throws ClassNotFoundException, SQLException, JAXBException, XMLStreamException, IOException, IllegalAccessException, InstantiationException, ParseException {
    Class dataClass = Class.forName("com.paterake.elexon.model.DataBody" + reportName);
    Class rdsClass = Class.forName("com.paterake.elexon.rds.Insert" + reportName);
    processData(reportName, fileName, dataClass, rdsClass);
  }

  public static void main(String[] args)
      throws IOException, XMLStreamException, JAXBException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
    Transformer transformer = new Transformer();
    //transformer.processData("PHYBMDATA",  "c:/temp/PHYBMDATA.xml");
    //transformer.processData("UOU2T14D", "c:/temp/UOU2T14D.xml");
    //transformer.processData("MessageListRetrieval", "c:/temp/MessageListRetrieval/MessageListRetrieval.xml");
    //transformer.processData("FORDAYDEM", "c:/temp/FORDAYDEM/FORDAYDEM-2018-06-26-2018-06-28.xml");
    transformer.processData("DERBMDATA", "c:/temp/DERBMDATA/DERBMDATA-2018-07-05-25.xml");
  }
}
