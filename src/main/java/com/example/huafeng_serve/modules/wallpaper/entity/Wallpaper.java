package com.example.huafeng_serve.modules.wallpaper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallpaper {
    private Integer id;

    private String picUrl;

    private Integer dianji;
}
