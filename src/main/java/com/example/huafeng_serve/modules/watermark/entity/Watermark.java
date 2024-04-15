package com.example.huafeng_serve.modules.watermark.entity;

import lombok.Data;

import java.util.List;

// 去水印返回类
@Data
public class Watermark {
    // 作者
    private String author;
    // uid
    private String uid;
    // 作者头像
    private String avatar;
    // 标题
    private String title;
    // 内容
    private String content;
    // 封面图
    private String cover;
    // 那个平台操作的
    private String platform;
    // 视频地址
    private String url;
    // 源视频地址
    private String sourceURL;
    // 音乐信息
    private Music music;
    // 区分图片还是视频 0-图片 1-视频
    private Integer type;
    // 图文地址
    private List<String> imgUrls;
    // 源图文地址
    private List<String> sourceImgUrls;
}
