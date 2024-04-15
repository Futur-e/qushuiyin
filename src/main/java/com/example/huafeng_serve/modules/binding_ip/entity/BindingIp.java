package com.example.huafeng_serve.modules.binding_ip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BindingIp {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String ip;

    // 是否禁用 0-开启  1-禁用
    private Integer isDisable;
}
