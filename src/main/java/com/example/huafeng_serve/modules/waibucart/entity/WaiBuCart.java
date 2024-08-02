package com.example.huafeng_serve.modules.waibucart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class WaiBuCart {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long cartNo;

    private Long CartId;

    private String brand;

    private String bluetooth;

}
