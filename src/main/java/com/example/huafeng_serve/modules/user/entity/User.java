package com.example.huafeng_serve.modules.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nickName;

    private String openId;

    private String mobile;

    private String avatarUrl;

    private String gender;

    private String country;

    private String province;

    private String city;

    private Integer evaluate;

    private LocalDateTime memberTime;

    private Integer operationNumber;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updatedTime;

    private LocalDateTime operationTime;
}
