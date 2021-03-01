package wiki.laona.utils;

import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.*;
import org.csource.common.NameValuePair;

/**
 * @description: 上传类
 * @author: laona
 * @create: 2021-02-28 21:42
 **/
public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * @param file 文件二进制
     * @param fileName 文件名
     * @param fileSize 文件大小
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] file, String fileName, long fileSize) throws Exception {

        NameValuePair[] metas = new NameValuePair[3];
        metas[0] = new NameValuePair("fileName", fileName);
        metas[1] = new NameValuePair("fileSize", String.valueOf(fileSize));
        metas[2] = new NameValuePair("fileExt", FilenameUtils.getExtension(fileName));
        String result = storageClient.upload_file1(file, FilenameUtils.getExtension(fileName), metas);
        return result;
    }

    /**
     *
     * @param storagePath  文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     * @throws Exception
     */
    public Integer delete_file(String storagePath){
        int result=-1;
        try {
            result = storageClient.delete_file1(storagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }



}
