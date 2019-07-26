package org.utils.uploadoss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.utils.DateUtil;
import org.utils.ReloadUtil;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

//阿里云储存OSS工具
public class OssUtil {
    private static String url=ReloadUtil.configuration.getString("oss.url");
    private static String path=ReloadUtil.configuration.getString("oss.path");
    private static String accesskeyid=ReloadUtil.configuration.getString("oss.accesskeyid");
    private static String accesskeysecret=ReloadUtil.configuration.getString("oss.accesskeysecret");
    private static String dept=ReloadUtil.configuration.getString("oss.dept");
    //上传
    public static String filePath(InputStream inputStream, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(url, accesskeyid, accesskeysecret);
        String folderName = DateUtil.date2String(new Date(), DateUtil.Y_M_D);
        String uuid = UUID.randomUUID().toString().replace("-","");
        String newFileName = folderName +"/"+ uuid;
        String suffixName = getSuffixName(fileName);
        String buckName = newFileName + suffixName;
        ossClient.putObject(dept,buckName , inputStream);
        ossClient.shutdown();
        return path+newFileName+suffixName;
    }

    public static void deleteFile(String imgagePath){
        OSS ossClient=null;
        try {
            String objectName = imgagePath.replace(path, "");
            ossClient = new OSSClientBuilder().build(url,accesskeyid,accesskeysecret);
            ossClient.deleteObject(dept, objectName);
        } catch (OSSException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (ossClient!=null){
                ossClient.shutdown();
            }
        }
    }


    private static String getSuffixName(String fileName) {
       return fileName.substring(fileName.lastIndexOf("."));
    }
}
