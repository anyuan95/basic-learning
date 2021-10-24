package org.example.basic.products.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

/**
 * @author anyuan
 * @since 2021-10-24 22:43
 */
public class OSSUtils {

    public static String upload(String fileName, InputStream inputStream){
        String endpoint = "https://oss-cn-qingdao.aliyuncs.com";
        String accessKeyId = "LTAI5tHSg5FbzHwRsGVs3S8E";
        String accessKeySecret = "QfHYNgqTIc4w51VfYruUgvMicWfa5c";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject("alextest-bucket", fileName, inputStream);
        ossClient.shutdown();
        String url = "https://alextest-bucket.oss-cn-qingdao.aliyuncs.com" +"/"+ fileName;
        return url;
    }

    public static void main(String[] args) {

    }
}
