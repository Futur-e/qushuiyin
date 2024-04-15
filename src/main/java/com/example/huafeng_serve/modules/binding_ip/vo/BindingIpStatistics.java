package com.example.huafeng_serve.modules.binding_ip.vo;

import lombok.Data;

import java.util.List;
@Data
public class BindingIpStatistics {
    private List<String> videos;

    private List<String> images;

    private List<String> all;
}
