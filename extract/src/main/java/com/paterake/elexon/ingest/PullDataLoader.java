package com.paterake.elexon.ingest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.paterake.elexon.ingest.s3.S3Util;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class PullDataLoader extends BaseDataLoader {

  public HttpURLConnection getUrlConnection(String urlString) throws IOException {
    System.out.println(urlString);
    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/xml");
    conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
    conn.connect();
    if (conn.getResponseCode() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
    }
    return conn;
  }

  public String loadDataS3(String bucketName, String urlString, String reportName, String fileName)
      throws IOException {
    HttpURLConnection conn = getUrlConnection(urlString);
    AmazonS3 s3Client = S3Util.getS3Client();

    S3Util.createBucket(s3Client, bucketName);
    S3Util.createFolder(s3Client, bucketName, reportName);
    String outputPath = String.format("%s/%s", reportName, fileName);
    System.out.println(outputPath);

    File scratchFile = File.createTempFile("prefix", "suffix");
    try {
      FileUtils.copyInputStreamToFile(conn.getInputStream(), scratchFile);
      if ("xml".equalsIgnoreCase(SERVICE_TYPE)) {
        XMLValidation validation = new XMLValidation();
        validation.validateData(scratchFile, reportName);
      }
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, outputPath, scratchFile);
      PutObjectResult putObjectResult = s3Client.putObject(putObjectRequest);
    } finally {
      if (scratchFile.exists()) {
        scratchFile.delete();
      }
    }
    conn.disconnect();

    return outputPath;
  }


  public String loadDataLocal(String urlString, String reportName, String fileName)
      throws IOException {
    HttpURLConnection conn = getUrlConnection(urlString);

    String outputPath = LOCAL_PATH + "/" + reportName + "/" + fileName;

    File rootDir = new File(LOCAL_PATH + "/" + reportName);
    rootDir.mkdirs();

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));

    String line;
    while ((line = br.readLine()) != null) {
      bw.write(line);
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
    conn.disconnect();

    if ("xml".equalsIgnoreCase(SERVICE_TYPE)) {
      XMLValidation validation = new XMLValidation();
      validation.validateData(outputPath, reportName);
    }

    return outputPath;
  }

  public String retrieveReport(boolean s3Ind, String reportName, String urlString, String fileName)
      throws IOException {
    String outputPath;
    if (s3Ind) {
      outputPath = loadDataS3(BUCKET_NAME, urlString, reportName, fileName);

    } else {
      outputPath = loadDataLocal(urlString, reportName, fileName);
    }
    return outputPath;
  }

}
