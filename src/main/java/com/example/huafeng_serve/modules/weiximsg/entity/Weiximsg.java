package com.example.huafeng_serve.modules.weiximsg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weiximsg {
    private String name;
    private String tel;
    private String city;
    private String source;
    private String company;
}
