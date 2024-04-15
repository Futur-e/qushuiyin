package com.example.huafeng_serve.common.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProjectProperties {
    /**
     * 去除水印资源地址
     */
    public static String unwatermarkFile;
    /**
     * 文件地址
     */
    public static String file;

    /**
     * 七牛云地址
     */
    public static String qiniuFile;
    /**
     * 开发环境
     */
    public static String env;

    @Value("${spring.profiles.active}")
    public void setEnv(String env) {
        this.env = env;
    }

    @Value("${unwatermark.unwatermarkFile}")
    public void setUnwatermarkFile(String unwatermarkFile) {
        this.unwatermarkFile = unwatermarkFile;
    }

    @Value("${unwatermark.file}")
    public void setFile(String file) {
        this.file = file;
    }

    @Value("${unwatermark.qiniuFile}")
    public void setQiniuFile(String file) {
        this.qiniuFile = file;
    }
}
