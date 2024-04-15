package com.example.huafeng_serve.modules.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.platform.entity.Platform;
import com.example.huafeng_serve.modules.platform.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {
    @Autowired
    private PlatformService platformService;

    @GetMapping("/list")
    public R<List<Platform>> selectByList() {
        List<Platform> platform = platformService.selectList();
        return R.success(platform);
    }
}
