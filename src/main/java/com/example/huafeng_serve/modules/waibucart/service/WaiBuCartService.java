package com.example.huafeng_serve.modules.waibucart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCart;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCartNo;

public interface WaiBuCartService extends IService<WaiBuCart> {
    WaiBuCart selectByCart(Long cartNo);

    int insert(Long cartNo);
}
