package com.paterake.elexon.ingest;

import java.lang.invoke.MethodHandles;
import java.util.zip.ZipInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class XMLValidation {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  private boolean isXMLValid(Source XSD, Source XML) {
    final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try {
      final Schema schema = factory.newSchema(XSD);
      final Validator validator = schema.newValidator();
      validator.validate(XML);
    } catch (IOException | SAXException e) {
      System.out.println("Exception: " + e.getMessage());
      return false;
    }
    return true;
  }

  public boolean isXMLValid(String XSD, String XML) {
    return isXMLValid(new StreamSource(new StringReader(XSD)),
        new StreamSource(new ByteArrayInputStream(XML.getBytes())));
  }

  public boolean isXMLValid(String XSD, File XML) {
    return isXMLValid(new StreamSource(new StringReader(XSD)), new StreamSource(XML));
  }

  public boolean isXMLValid(String XSD, InputStream XML) {
    return isXMLValid(new StreamSource(XSD), new StreamSource(XML));
  }

  public boolean isXMLValid(String XSD, Reader XML) {
    return isXMLValid(new StreamSource(XSD), new StreamSource(XML));
  }

  public String extractXsdFromZipFile(String xsdName, String xsdBundle) {
    String xsdContext = null;
    File file = new File(this.getClass().getClassLoader().getResource(xsdBundle).getFile());
    try (ZipFile zipFile = new ZipFile(file)) {
      Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
      Predicate<ZipEntry> isXsd = ze -> ze.getName().toUpperCase()
          .matches(".*/" + xsdName.toUpperCase() + ".XSD");
      Comparator<ZipEntry> bySize =
          (ze1, ze2) -> Long.valueOf(ze2.getSize() - ze1.getSize()).intValue();
      List<ZipEntry> zipEntryList = zipFile.stream()
          .filter(isFile.and(isXsd))
          .sorted(bySize)
          .collect(Collectors.toList());
      System.out.println(zipEntryList.get(0));
      try (InputStream inputStream = zipFile.getInputStream(zipEntryList.get(0));
          BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        xsdContext = reader.lines().collect(Collectors.joining());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return xsdContext;
  }

  public String extractXsdFromZipStream(String xsdName, String xsdBundle) {
    String xsdContext = null;
    InputStream in = getClass().getClassLoader().getResourceAsStream(xsdBundle);
    try (ZipInputStream zis = new ZipInputStream(in)) {
      ZipEntry zipEntry;
      boolean foundInd = false;
      while (!foundInd && (zipEntry = zis.getNextEntry()) != null) {
        if (!zipEntry.isDirectory() && zipEntry.getName().toUpperCase()
            .matches(".*/" + xsdName.toUpperCase() + ".XSD")) {
          foundInd = true;
          System.out.println(zipEntry);
          try (BufferedReader reader = new BufferedReader(new InputStreamReader(zis))) {
            xsdContext = reader.lines().collect(Collectors.joining());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      if (!foundInd) {
        throw new IndexOutOfBoundsException();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return xsdContext;
  }


  public String extractXsd(String xsdName, String xsdBundle) {
    return extractXsdFromZipStream(xsdName, xsdBundle);
  }

  public String extractXsd(String xsdName) throws FileNotFoundException {
    String xsdValue;
    try {
      xsdValue = extractXsd(xsdName, "REST_API_XML_RESPONSE_XSD_v1.2.zip");
    } catch (IndexOutOfBoundsException e) {
      try {
        xsdValue = extractXsd(xsdName, "REST_API_XML_Response_XSD_Phase_2_v1.3.zip");
      } catch (IndexOutOfBoundsException e2) {
        try {
          xsdValue = extractXsd(xsdName, "REST_API_XML_Response_XSD_Phase3_v0.3.zip");
        } catch (IndexOutOfBoundsException e3) {
          throw new FileNotFoundException("XSD not found for report: " + xsdName);
        }
      }
    }
    return xsdValue;
  }

  public void validateData(File xmlFile, String xsdTag) throws IOException {
    String XSD = null;
    boolean validInd = true;
    try {
      XSD = extractXsd(xsdTag);
      validInd = isXMLValid(XSD, xmlFile);
    } catch (FileNotFoundException e) {
      validInd = true;
    }
    if (!validInd) {
      LOGGER.info("XML Data is invalid against XSD");
      //throw new IOException("XML Data is invalid against XSD");
    }
  }

  public void validateData(String xmlFileName, String xsdTag) throws IOException {
    validateData(new File(xmlFileName), xsdTag);
  }

  public static void main(String[] args) throws IOException {

    XMLValidation validation = new XMLValidation();
    //validation.validateData("c:/temp/out.xml", "B1720");
    System.out.println(validation.extractXsd("SYSDEM"));
    //validation.validateData("c:/temp/PHYBMDATA/PHYBMDATA-2018-06-15-48.xml", "PHYBMDATA");

    //System.out.println("EmployeeRequest.xml validates against Employee.xsd? " + validateXMLSchema("Employee.xsd", "EmployeeRequest.xml"));
    //System.out.println("EmployeeResponse.xml validates against Employee.xsd? " + validateXMLSchema("Employee.xsd", "EmployeeResponse.xml"));
    //System.out.println("employee.xml validates against Employee.xsd? " + validateXMLSchema("Employee.xsd", "employee.xml"));

  }


}