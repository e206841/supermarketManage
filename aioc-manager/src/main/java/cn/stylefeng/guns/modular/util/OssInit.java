package cn.stylefeng.guns.modular.util;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssInit implements InitializingBean {

    @Value("${endpoint}")
    private String endpoint;

    @Value("${bucket_name}")
    private String bucketName;

    @Override
    public void afterPropertiesSet() throws Exception {
        OssMap.ossMap.put("endpoint",endpoint);
        OssMap.ossMap.put("bucketName",bucketName);
    }
}

