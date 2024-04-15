package com.example.huafeng_serve.modules.wallpaper.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.wallpaper.entity.Wallpaper;
import com.example.huafeng_serve.modules.wallpaper.mapper.WallpaperMapper;
import com.example.huafeng_serve.modules.wallpaper.service.WallpaperService;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Wallpaperimpl implements WallpaperService {
    @Autowired
    private WallpaperMapper wallpaperMapper;

    @Override
    public IPage<Wallpaper> selectByPage(IPage iPage, PageParam param) {
        IPage<Wallpaper> page = wallpaperMapper.selectByPage(iPage,param);
        return page;
    }
}
