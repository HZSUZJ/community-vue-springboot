package com.su.community.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TXCloudProvider {
    private String secretId="";
    private String secretKey="";

    COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    Region region = new Region("ap-guangzhou");
    ClientConfig clientConfig = new ClientConfig(region);
    String bucketName = "community-1254053068";

    public String upload(InputStream fileStream,String filename,long inputStreamLength){
        String generateFilename="";
        String[] filePaths=filename.split("\\.");
        if(filePaths.length>1){
            generateFilename= UUID.randomUUID().toString()+"."+filePaths[filePaths.length-1];
        }
        TransferManager transferManager = createTransferManager();
        String key = generateFilename;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStreamLength);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, fileStream, objectMetadata);
        try {
            Upload upload = transferManager.upload(putObjectRequest);
            UploadResult uploadResult = upload.waitForUploadResult();
        } catch (CosServiceException e) {
            e.printStackTrace();
            return null;
        } catch (CosClientException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        shutdownTransferManager(transferManager);
        COSClient cosclient = new COSClient(cred, clientConfig);
        URL url= cosclient.getObjectUrl(bucketName, key);
        return url.toString();
    }

    // 创建 TransferManager 实例，这个实例用来后续调用高级接口
    TransferManager createTransferManager() {
        COSClient cosClient = new COSClient(cred, clientConfig);
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        TransferManager transferManager = new TransferManager(cosClient, threadPool);
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
        transferManagerConfiguration.setMultipartUploadThreshold(5*1024*1024);
        transferManagerConfiguration.setMinimumUploadPartSize(1*1024*1024);
        transferManager.setConfiguration(transferManagerConfiguration);
        return transferManager;
    }
    void shutdownTransferManager(TransferManager transferManager) {
        transferManager.shutdownNow(true);
    }
}
