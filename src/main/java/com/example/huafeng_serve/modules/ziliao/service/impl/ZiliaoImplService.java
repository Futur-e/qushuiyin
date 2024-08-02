package com.example.huafeng_serve.modules.ziliao.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import com.example.huafeng_serve.modules.ziliao.entity.Ziliao;
import com.example.huafeng_serve.modules.ziliao.mapper.ZiliaoMapper;
import com.example.huafeng_serve.modules.ziliao.service.ZiliaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZiliaoImplService implements ZiliaoService {

    @Autowired
    private ZiliaoMapper ziliaoMapper;


    @Override
    public IPage<Ziliao> selectByPage(IPage iPage, PageParam param) {
        return ziliaoMapper.selectByPage(iPage, param);
    }

    @Override
    public String insertZiliao(Ziliao param) {
        ziliaoMapper.insert(param);return "success";
    }
}
