package com.paterake.elexon.ingest.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class S3Util {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());
  private static final String SUFFIX = "/";

  public static AmazonS3 getS3Client() {
    AmazonS3 s3client = AmazonS3ClientBuilder
        .standard()
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .withRegion(Regions.EU_WEST_1)
        .build();
    return s3client;
  }

  public static void createBucket(AmazonS3 s3client, String bucketName) {
    if (s3client.doesBucketExistV2(bucketName)) {
      LOGGER.info("Bucket name exists:" + bucketName);
      return;
    } else {
      LOGGER.info("Creating bucket: " + bucketName);
      s3client.createBucket(bucketName);
    }
  }

  public static void createFolder(AmazonS3 client, String bucketName, String folderName) {
    // create meta-data for your folder and set content-length to 0
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(0);
    // create empty content
    InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
    // create a PutObjectRequest passing the folder name suffixed by /
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
        folderName + SUFFIX, emptyContent, metadata);
    // send request to S3 to create folder
    client.putObject(putObjectRequest);
  }

  public static void listBucket(AmazonS3 s3client) {
    for (Bucket bucket : s3client.listBuckets()) {
      System.out.println(" - " + bucket.getName());
    }
  }

  public static void listBucket(AmazonS3 s3Client, String bucketName) {

    ObjectListing objectListing = s3Client.listObjects(bucketName);
    for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
      System.out.println(os.getKey());
    }

    ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(2);
    ListObjectsV2Result result;

    do {
      result = s3Client.listObjectsV2(req);

      for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
        System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
      }
      // If there are more than maxKeys keys in the bucket, get a continuation token
      // and list the next objects.
      String token = result.getNextContinuationToken();
      System.out.println("Next Continuation Token: " + token);
      req.setContinuationToken(token);
    } while (result.isTruncated());

    ListObjectsV2Request req2 = new ListObjectsV2Request().withBucketName(bucketName)
        .withPrefix("/").withDelimiter("/");
    ListObjectsV2Result listing = s3Client.listObjectsV2(req2);
    for (String commonPrefix : listing.getCommonPrefixes()) {
      System.out.println(commonPrefix);
    }
    for (S3ObjectSummary summary : listing.getObjectSummaries()) {
      System.out.println(summary.getKey());
    }
  }

  public static void clearBucket(AmazonS3 s3Client, String bucketName) {
    ObjectListing objectListing = s3Client.listObjects(bucketName);
    while (true) {
      Iterator<S3ObjectSummary> objIter = objectListing.getObjectSummaries().iterator();
      while (objIter.hasNext()) {
        s3Client.deleteObject(bucketName, objIter.next().getKey());
      }
      if (objectListing.isTruncated()) {
        objectListing = s3Client.listNextBatchOfObjects(objectListing);
      } else {
        break;
      }
      VersionListing versionList = s3Client
          .listVersions(new ListVersionsRequest().withBucketName(bucketName));
      while (true) {
        Iterator<S3VersionSummary> versionIter = versionList.getVersionSummaries().iterator();
        while (versionIter.hasNext()) {
          S3VersionSummary vs = versionIter.next();
          s3Client.deleteVersion(bucketName, vs.getKey(), vs.getVersionId());
        }

        if (versionList.isTruncated()) {
          versionList = s3Client.listNextBatchOfVersions(versionList);
        } else {
          break;
        }
      }
    }
  }

  public static void deleteBucket(AmazonS3 s3client, String bucketName) {
    S3Util.clearBucket(s3client, bucketName);
    s3client.deleteBucket(bucketName);
  }

  public static InputStream getObject(AmazonS3 s3client, String bucketName, String objectPath) {
    S3Object o = s3client.getObject(bucketName, objectPath);
    S3ObjectInputStream s3is = o.getObjectContent();
    return s3is;
  }

  public static void getObject(AmazonS3 s3client, String bucketName, String objectPath,
      String outputName) throws IOException {
    try {
      S3Object o = s3client.getObject(bucketName, objectPath);
      S3ObjectInputStream s3is = o.getObjectContent();
      FileOutputStream fos = new FileOutputStream(new File(outputName));
      byte[] read_buf = new byte[1024];
      int read_len = 0;
      while ((read_len = s3is.read(read_buf)) > 0) {
        fos.write(read_buf, 0, read_len);
      }
      s3is.close();
      fos.close();
    } catch (AmazonServiceException e) {
      System.err.println(e.getErrorMessage());
      System.exit(1);
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  public static void main(String[] args) throws IOException {
    String bucketName = "bmrs";
    AmazonS3 s3client = S3Util.getS3Client();
    //S3Util.createBucket(s3client, bucketName);
    //S3Util.listBucket(s3client, bucketName);
    S3Util.clearBucket(s3client, bucketName);
  }

}
