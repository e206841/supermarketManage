package cn.stylefeng.guns.modular.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Configuration
public class OssConfiguration {

    @Value("${endpoint}")
    private String endpoint;

    @Value("${access_key}")
    private String accessKey;

    @Value("${secret_key}")
    private String secretKey;

    @Value("${bucket_name}")
    private String bucketName;


    @Bean("defaultAmazonS3")
    public AmazonS3 defaultAmazonS3(){
        ClientConfiguration config = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpointConfig =
                new AwsClientBuilder.EndpointConfiguration(endpoint, "<REGION>");
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        AmazonS3 s3 = AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfig)
                .withClientConfiguration(config)
                .withCredentials(awsCredentialsProvider)
                .disableChunkedEncoding()
                .withPathStyleAccessEnabled(true)
                .build();
        creatBucketIfAbsent(s3);
        return s3;
    }

    public void creatBucketIfAbsent(AmazonS3 s3){
        List<Bucket> buckets = s3.listBuckets();
        boolean absent = true;
        for (Bucket a : buckets) {
            if(a.getName().equals(bucketName)){
                absent=false;
                break;
            }
        }
        try {
            // 创建存储空间。
            if(absent) {
                s3.createBucket(bucketName);
            }
        } catch (AmazonServiceException e) {
            // 捕获异常。
            e.printStackTrace();
        }
    }

}

