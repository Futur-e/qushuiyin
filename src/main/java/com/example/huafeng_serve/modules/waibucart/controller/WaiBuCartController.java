package com.example.huafeng_serve.modules.waibucart.controller;

import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCart;
import com.example.huafeng_serve.modules.waibucart.entity.WaiBuCartNo;
import com.example.huafeng_serve.modules.waibucart.service.WaiBuCartNoService;
import com.example.huafeng_serve.modules.waibucart.service.WaiBuCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiBuCart")
@Slf4j
public class WaiBuCartController {

    @Autowired
    private WaiBuCartService waiBuCartService;
    @Autowired
    private WaiBuCartNoService waiBuCartNoService;

    @GetMapping("/select/{cartNo}")
    public R<WaiBuCart> selectByCartNo(@PathVariable Long cartNo) {
        log.info("参数是：{}", cartNo);
        WaiBuCart waiBuCart = waiBuCartService.selectByCart(cartNo);
        if (!ObjectUtils.isEmpty(waiBuCart)) {
            return R.success(waiBuCart);
        } else {
            R<WaiBuCart> r = new R<>();
            WaiBuCartNo waiBuCartNo = waiBuCartNoService.selectByCartNo(cartNo);
            if (!ObjectUtils.isEmpty(waiBuCartNo)){
                return R.success(waiBuCart);
            }else {
                waiBuCartNoService.insert(cartNo);
                r.setMsg("插入成功");
                r.setData(waiBuCart);
                r.setCode(200);
            }
            return r;
        }
    }
}
