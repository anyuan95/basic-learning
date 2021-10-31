package org.example.basic.products.aliyun.oss;

import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @date 2021-10-27 16:23
 */
public class OSSUtils {

    /**
     * OSS创建桶
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     */
    public void createBucket(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建存储空间
        ossClient.createBucket(bucketName);
        // 关闭OSSClient
        ossClient.shutdown();
    }

    /**
     * 上传文件（字符串）
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     * @param objectName
     * @param fileContent
     */
    public void upload(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String objectName,
                       String fileContent) {
        upload(endpoint, accessKeyId, accessKeySecret, bucketName, objectName,
                new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 上传文件（字节数组）
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     * @param objectName
     * @param data
     */
    public void upload(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String objectName,
                       byte[] data) {
        upload(endpoint, accessKeyId, accessKeySecret, bucketName, objectName,
                new ByteArrayInputStream(data));
    }

    /**
     * 上传文件（输入流）
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     * @param objectName
     * @param inputStream
     */
    public void upload(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String objectName,
                       InputStream inputStream) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, objectName, inputStream);
        // 关闭OSSClient
        ossClient.shutdown();
    }

    /**
     * 下载文件（字节数组）
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     * @param objectName
     * @return
     */
    public byte[] download(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String objectName) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream inputStream = ossObject.getObjectContent();
        try {
            return IoUtil.readBytes(inputStream);
        } finally {
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            inputStream.close();
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 列举桶中所有文件的描述信息
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     * @param bucketName
     * @return
     */
    public List<FileMetadata> list(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        try {
            // objectListing.getObjectSummaries获取所有文件的描述信息。
            return objectListing.getObjectSummaries()
                    .stream()
                    .map(summary -> FileMetadata.builder().fileName(summary.getKey()).size(summary.getSize()).build())
                    .collect(Collectors.toList());
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    @Data
    @Builder
    public static class FileMetadata {
        private String fileName;
        private Long size;
    }

}
