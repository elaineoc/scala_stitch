package s3Client
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.amazonaws.services.s3.model.S3Object

/**
  * S3 Client Factory
  *
  */
object S3Client {

  def apply(): AmazonS3 = {
		val secretKey="xxx"
		val accessKey="xxx"

    val region      = "us-west-2"

		val credentials = new BasicAWSCredentials(accessKey, secretKey)
		val credentialsProvider = new AWSStaticCredentialsProvider(credentials)

    val client = AmazonS3ClientBuilder.standard()
      .withRegion(region)
      .withCredentials(credentialsProvider)
      .build()

		client
	}

}
