package com.example.huafeng_serve.modules.weiximsg.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxMaInMemoryConfig {
    private String appid;
    private String secret;
}
