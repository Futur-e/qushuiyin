package com.example.huafeng_serve.modules.binding_ip.vo;

import com.example.huafeng_serve.common.utils.PageUtil;
import lombok.Data;

@Data
public class BindingIpParam extends PageUtil {
    private String ip;

    // 是否禁用 0-开启  1-禁用
    private Integer isDisable;
}
