package com.example.huafeng_serve.common.utils;
import com.example.huafeng_serve.modules.user.service.UserService;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class DownloadUtil {
    public static String downLoad (String picUrl, Watermark watermark){
        try {
            //网络图片资源的url（可以把这个放参数中动态获取）
            //创建URL对象，参数传递一个String类型的URL解析地址
            URL url = new URL(picUrl);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            //从HTTP响应消息获取状态码
            int code = huc.getResponseCode();
            if (code == 200) {
                //获取输入流
                InputStream ips = huc.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = ips.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.close();
                return uploadFileByBytes(bos.toByteArray(),watermark);
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    private static String uploadFileByBytes(byte[] bytes, Watermark watermark) throws Exception {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {
                bytes[i] += 256;
            }
        }
        String realPath = System.getProperty("user.dir") + File.separator + "upload" ;
        String fileName ="";

        if (watermark.getType() == 0) {
            fileName = File.separator+Math.random() * 100 +".png";
        }else if (watermark.getType() == 1) {
            fileName = File.separator+Math.random() * 100 +".mp4";
        }
        //文件路径
        String url = realPath + fileName;
        //判断文件路径是否存在，如果没有则新建文件夹
        File files = new File(realPath);
        if(!files.exists()){
            files.mkdirs();
        }
        //把文件写入到指定路径下
        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(url, false))){
            out.write(bytes);
        }
        return ProjectProperties.unwatermarkFile + fileName;
    }
}