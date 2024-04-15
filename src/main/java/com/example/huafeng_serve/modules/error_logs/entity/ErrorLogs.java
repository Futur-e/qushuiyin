package com.example.huafeng_serve.modules.error_logs.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorLogs {
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户Id
    private Integer userId;
    // 错误的IP
    private String url;
    // 0-接口 1-IP错误
    private Integer type;
    // 错误描述
    @TableField(value = "`describe`")
    private String describe;
    // 0-图片 1-视频
    private Integer analysisType;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updatedTime;
}
