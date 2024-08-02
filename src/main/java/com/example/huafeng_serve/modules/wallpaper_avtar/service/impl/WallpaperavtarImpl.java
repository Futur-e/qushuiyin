package com.example.huafeng_serve.modules.wallpaper_avtar.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import com.example.huafeng_serve.modules.wallpaper_avtar.entity.Avtar;
import com.example.huafeng_serve.modules.wallpaper_avtar.mapper.WallpaperavtarMapper;
import com.example.huafeng_serve.modules.wallpaper_avtar.service.WallpaperavtarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WallpaperavtarImpl implements WallpaperavtarService {

    @Autowired
    private WallpaperavtarMapper wallpaperavtarMapper;
    @Override
    public IPage<Avtar> selectByPage(IPage iPage, PageParam param,Long type) {
        IPage<Avtar> page = wallpaperavtarMapper.selectByPage(iPage,param,type);
        return page;
    }
}
