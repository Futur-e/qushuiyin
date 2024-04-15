package com.example.huafeng_serve.modules.invitation_logs.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvitationLogs {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer inviterId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updatedTime;
}
