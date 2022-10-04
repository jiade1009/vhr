package org.javaboy.vhr.config;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2020-03-02 23:26
 */
public class FastDFSUtils {
    private static StorageClient1 client1;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;

    static {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            client1 = new StorageClient1(trackerServer, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static String upload(MultipartFile file) {
        String oldName = file.getOriginalFilename();
        try {
            return client1.upload_file1(file.getBytes(), oldName.substring(oldName.lastIndexOf(".") + 1), null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param fileContent 文件的内容,字节数组 (MultipartFile可获取)
     * @param extName     文件扩展名
     * @param metas       文件扩展信息 传null就行
     *                    NameValuePair[] meta_list = new NameValuePair[1];
     *                    meta_list[0] = new NameValuePair("author","小舞");
     * @return String[]s   s[1]文件上传所存储的组名 s[2]文件存储路径
     * @throws IOException
     */
    public static String[] uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws IOException {
        try {
            //upload_file1 比 upload_file多封装一层
            return client1.upload_file(fileContent, extName, metas);
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件方法2
     *
     * @param fileName 文件全路径
     * @param extName  文件扩展名,不包含（.）
     * @param metas    文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(String fileName, String extName, NameValuePair[] metas) {
        try {
            //upload_file1 比 upload_file多封装一层
            return client1.upload_file1(fileName, extName, metas);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 获取文件信息
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            //获取文件信息
            return client1.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 文件下载
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名(去除组名)
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            //通过StorageClient下载文件
            byte[] fileByte = client1.download_file(groupName, remoteFileName);
            //将字节数组转换成字节输入流
            return new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 文件删除实现
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名(去除组名)
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        try {
            //通过StorageClient删除文件
            client1.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取组信息
     * @param groupName :组名
     */
    public static StorageServer getStorage(String groupName) {
        try {
            //通过trackerClient获取Storage组信息
            return trackerClient.getStoreStorage(trackerServer, groupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @param groupName :组名
     * @param remoteFileName ：文件存储完整名
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) {
        try {
            //获取服务信息
            return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /***
     * 获取Tracker服务地址
     */
    public static String getTrackerUrl() {
        try {
            //获取Tracker地址
            return "http://" + trackerServer.getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
