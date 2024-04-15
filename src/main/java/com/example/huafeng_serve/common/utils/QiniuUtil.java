package com.example.huafeng_serve.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;

// 七牛云存储kodo
public class QiniuUtil {
    private static final String ACCESS_KEY = "ACCESS_KEY";
    private static final String SECRET_KEY = "SECRET_KEY";
    // 空间名称
    private static final String BUCKET_NAME = "BUCKET_NAME";
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private BucketManager bucketManager;
    private Integer type;
    public QiniuUtil() {
        Configuration cfg = new Configuration(Region.huabei()); //根据自己所在区域选择合适的区域
        this.bucketManager = new BucketManager(auth, cfg);
    }
    public QiniuUtil(Integer type) {
        Configuration cfg = new Configuration(Region.huabei()); //根据自己所在区域选择合适的区域
        this.bucketManager = new BucketManager(auth, cfg);
        this.type = type;
    }

    /**
     * 从指定的URL抓取资源，并将其存储到七牛云指定的存储空间中
     *
     * @param urlString 网络上的资源URL
     * @return FetchRet 包含了抓取操作返回的信息
     */
    public String fetchFromUrl(String urlString) {
        try {
            // 文件名称随机数 + 获取当前时间戳（秒）
            String fileName = Math.random() * 100 + "-" + System.currentTimeMillis() / 1000;
            if (this.type == 0) {
                // 图片
                fileName = fileName + ".png";
            } else if (this.type == 1) {
                // 视频
                fileName = fileName + ".mp4";
            }
//            if(ProjectProperties.env.equals("dev")){
//                // 测试的情况下
//                return "需要下载";
//            }else if (ProjectProperties.env.equals("prod")){
                FetchRet fetchRet = bucketManager.fetch(urlString, BUCKET_NAME, fileName);
                String filePath = ProjectProperties.qiniuFile + "/" + fetchRet.key;
                // 3600是一小时 所以7200 2小时
                String downloadRUL = auth.privateDownloadUrl(filePath, 7200);
                return downloadRUL;
//            }
//            return null;
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }


}