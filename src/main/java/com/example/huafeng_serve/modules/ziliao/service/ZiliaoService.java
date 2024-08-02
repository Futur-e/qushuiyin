package com.example.huafeng_serve.modules.ziliao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import com.example.huafeng_serve.modules.ziliao.entity.Ziliao;

public interface ZiliaoService {
    IPage<Ziliao> selectByPage(IPage iPage, PageParam param);

    String insertZiliao(Ziliao param);
}
