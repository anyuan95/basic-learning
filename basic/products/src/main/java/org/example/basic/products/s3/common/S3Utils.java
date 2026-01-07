package org.example.basic.products.s3.common;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectListing;
import lombok.Data;

/**
 *
 * @author anyuan02
 * @date 2025-08-11 14:42
 */
@Data
public class S3Utils {

    /**
     * https://blog.csdn.net/u012586326/article/details/130411926
     * 
     * @return
     */
    public static AmazonS3 buildS3Client() {
//        {
//            "endpoint":"msstest.vip.sankuai.com", "bucket":"multitable-bucket", "secure":true, "use_kms":false, "appkey":
//            "com.sankuai.aiagent.multitable", "access_key":"b6ddade6a0b44dc8851a91867d661f95", "access_secret":
//            "43cdf225bd2b4a8a8d266b1472ae8ba1"
//        }
        final String endpoint = "msstest.vip.sankuai.com",
                accessKey = "b6ddade6a0b44dc8851a91867d661f95",
                secretKey = "43cdf225bd2b4a8a8d266b1472ae8ba1";

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setSignerOverride("S3SignerType");
        clientConfig.setProtocol(Protocol.HTTPS);

        // 禁用证书检查，避免https自签证书校验失败
        System.setProperty("com.amazonaws.sdk.disableCertChecking", "true");
        // 屏蔽 AWS 的 MD5 校验，避免校验导致的下载抛出异常问题
        System.setProperty("com.amazonaws.services.s3.disableGetObjectMD5Validation", "true");
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        // 创建 S3Client 实例
        AmazonS3 s3client = new AmazonS3Client(awsCredentials, clientConfig);
        s3client.setEndpoint(endpoint);
        s3client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        return s3client;
    }

    public static void main(String[] args) {
        final S3Utils s3Utils = new S3Utils();
        final AmazonS3 s3Client = S3Utils.buildS3Client();
        final boolean checkS3Credentials = s3Client.doesObjectExist("multitable-bucket", "check_s3_credentials");
        System.out.println(checkS3Credentials);

        final String bucketName = "multitable-bucket";
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        objectListing.getObjectSummaries().stream()
                .limit(10)
                .forEach(summary -> {
                    System.out.println("文件名: " + summary.getKey());
                    System.out.println("大小: " + summary.getSize() + " 字节");
                    System.out.println("最后修改时间: " + summary.getLastModified());
                    System.out.println("存储类型: " + summary.getStorageClass());
                    System.out.println("-----------------------------");
                });

//        // 处理分页结果
//        while (objectListing.isTruncated()) {
//            ObjectListing nextObjectListing = s3Client.listNextBatchOfObjects(objectListing);
//            nextObjectListing.getObjectSummaries().forEach(summary -> {
//                System.out.println("文件名: " + summary.getKey());
//                System.out.println("大小: " + summary.getSize() + " 字节");
//                System.out.println("最后修改时间: " + summary.getLastModified());
//                System.out.println("存储类型: " + summary.getStorageClass());
//                System.out.println("-----------------------------");
//            });
//            objectListing = nextObjectListing;
//        }
//

    }
}
