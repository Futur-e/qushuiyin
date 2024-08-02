package com.example.huafeng_serve.modules.weiximsg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCartNo;
import com.example.huafeng_serve.modules.waibucart.mapper.WaiBuCartNoMapper;
import com.example.huafeng_serve.modules.waibucart.service.WaiBuCartNoService;
import com.example.huafeng_serve.modules.weiximsg.entity.Weiximsg;
import com.example.huafeng_serve.modules.weiximsg.mapper.WeiximsgMapper;
import com.example.huafeng_serve.modules.weiximsg.service.WeiximsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeiximsgServiceImpl extends ServiceImpl<WeiximsgMapper, Weiximsg> implements WeiximsgService {
    @Override
    public void insert(Weiximsg weiximsg1) {
        Weiximsg weiximsg  = new Weiximsg();
        weiximsg.setName(weiximsg1.getName());
        weiximsg.setTel(weiximsg1.getTel());
        weiximsg.setCity(weiximsg1.getCity());
        weiximsg.setSource(weiximsg1.getSource());
        weiximsg.setCompany(weiximsg1.getName());
        baseMapper.insert(weiximsg);
    }
}
