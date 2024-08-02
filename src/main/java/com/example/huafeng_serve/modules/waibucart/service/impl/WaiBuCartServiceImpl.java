package com.example.huafeng_serve.modules.waibucart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCart;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCartNo;
import com.example.huafeng_serve.modules.waibucart.mapper.WaiBuCartMapper;
import com.example.huafeng_serve.modules.waibucart.service.WaiBuCartService;
import org.springframework.stereotype.Service;

@Service
public class WaiBuCartServiceImpl extends ServiceImpl<WaiBuCartMapper, WaiBuCart> implements WaiBuCartService {

    @Override
    public WaiBuCart selectByCart(Long cartNo) {
        LambdaQueryWrapper<WaiBuCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WaiBuCart::getCartNo, cartNo);
        WaiBuCart waiBuCart = baseMapper.selectOne(wrapper);
        return waiBuCart;
    }

    @Override
    public int insert(Long cartNo) {
        WaiBuCart waiBuCart = new WaiBuCart();
        waiBuCart.setCartNo(cartNo);
        return baseMapper.insert(waiBuCart);
    }
}
