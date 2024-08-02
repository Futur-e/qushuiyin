package com.example.huafeng_serve.modules.weiximsg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCartNo;
import com.example.huafeng_serve.modules.weiximsg.entity.Weiximsg;

public interface WeiximsgService  extends IService<Weiximsg> {

    void insert(Weiximsg weiximsg);
}
