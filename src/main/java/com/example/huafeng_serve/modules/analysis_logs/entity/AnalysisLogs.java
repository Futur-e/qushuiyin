package com.example.huafeng_serve.modules.analysis_logs.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnalysisLogs {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String url;

    private String platform;

    private String videoUrl;

    private String videoDownloadUrl;
    // 区分图片还是视频 0-图片 1-视频
    private Integer type;

    private String imgUrls;

    private String imgDownloadUrls;

    private String title;

    private String content;

    private String cover;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
