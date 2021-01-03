package cn.stylefeng.guns.modular.util;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class OssFileUtils {

    @Resource(name = "defaultAmazonS3")
    private AmazonS3 s3;

    @Value("${endpoint}")
    private String endpoint;

    @Value("${bucket_name}")
    private String bucketName;

    public String upload(String name, MultipartFile file) {
        String url=null;
        try {
            //获取输入流
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            //String newFileName = System.currentTimeMillis()+name;
            s3.putObject(bucketName, name, file.getInputStream(), objectMetadata);
//            url="https://"+bucketName+"."+endpoint+"/png/"+name;
            url="https://"+endpoint+"/"+bucketName+"/"+name;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

}

