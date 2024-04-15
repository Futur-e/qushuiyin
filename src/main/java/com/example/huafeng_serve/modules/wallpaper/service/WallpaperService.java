package com.example.huafeng_serve.modules.wallpaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.wallpaper.entity.Wallpaper;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;

public interface WallpaperService {

    IPage<Wallpaper> selectByPage(IPage iPage, PageParam param);
}
