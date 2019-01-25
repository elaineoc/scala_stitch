import s3Client.S3Client
//package com.zendesk.edge.zcm.storage.s3

import scala.collection.JavaConversions._
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.amazonaws.services.s3.model.PutObjectRequest
import java.io.SequenceInputStream
import org.apache.commons.io.FileUtils
import java.io.File


object Main extends App {

  val client = S3Client()
  val one = client.getObject("labday-eocarroll-nagius", "123/call_recordings/123/leg_recordings/one.mp3")
  val ClipA = one.getObjectContent
  val ClipB = client.getObject("labday-eocarroll-nagius", "123/call_recordings/123/leg_recordings/two.mp3").getObjectContent

  val appendedFiles = new SequenceInputStream(ClipA, ClipB)
  val scratchFile = File.createTempFile("prefix", "suffix");
  FileUtils.copyInputStreamToFile(appendedFiles, scratchFile)

  val putObjectRequest = new PutObjectRequest("labday-eocarroll-nagius", "123/call_recordings/123/scala_call_recording.mp3", scratchFile);
  client.putObject(putObjectRequest)
}
