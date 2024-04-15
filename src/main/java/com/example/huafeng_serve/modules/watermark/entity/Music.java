package com.example.huafeng_serve.modules.watermark.entity;

import lombok.Data;

// 视频音乐信息
@Data
public class Music {
    // 音乐作者
    private String author;
    // 音乐作者头像
    private String avatar;
    // 音乐地址
    private String url;
}
