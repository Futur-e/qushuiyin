package com.example.huafeng_serve.modules.utils;

import com.example.huafeng_serve.common.utils.ProjectProperties;
import com.example.huafeng_serve.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
@RequestMapping(value = "/uploads")
public class FileController {
    /**
     *  多文件上传流程
     *      1.前端上传多个文件
     *      2.后台使用请求对象(MultipartHttpServletRequest)接收整个请求流
     *      3.获取MultipartFile集合
     *      4.定义缓冲字节输出流
     *      5.遍历MultipartFile集合
     *      6.获取每一个MultipartFile对象
     *      7.定义上传路径
     *      8.判断上传文件是否为空（也就是没有上传）
     *      9.如果不为空，则通过缓冲字节输出流写入到上传路径
     */
    @PostMapping
    public R<String> upload(MultipartFile file) {
        if(file.isEmpty()){
            return R.error("文件为空,请重新上传！");
        }

        String filePath = System.getProperty("user.dir") + File.separator + "upload" + File.separator + "file" + File.separator;
        String uploadFileName = uploadFile(file, filePath);
        if (uploadFileName!=null){
            return R.success(ProjectProperties.unwatermarkFile + "/file/" + uploadFileName);
        }
        return R.error("上传失败！");
    }


    /**
     * 文件上传工具类
     * @param file
     * @param filePath
     * @return
     */
    private String uploadFile(MultipartFile file, String filePath) {
        // 获取文件名
        String fileName = Math.random() * 100 + file.getOriginalFilename();
        // 获取日期，拼接到文件名中，避免文件名重复

        File newFile = new File(filePath,  fileName);
        // 检测是否存在该目录
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            System.out.println("写入文件"+filePath);
            file.transferTo(newFile);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}