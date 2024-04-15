package com.example.huafeng_serve.modules.wallpaper.vo;

import com.example.huafeng_serve.common.utils.PageUtil;
import lombok.Data;

@Data
public class PageParam extends PageUtil {
    private String ip;

    // 是否禁用 0-开启  1-禁用
    private Integer isDisable;
}
