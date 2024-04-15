package com.example.huafeng_serve.modules.wallpaper.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.vo.AnalysisLogsParam;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpParam;
import com.example.huafeng_serve.modules.wallpaper.entity.Wallpaper;
import com.example.huafeng_serve.modules.wallpaper.service.WallpaperService;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/wallpaper")
public class wallpaperController {
    @Autowired
    private WallpaperService wallpaperService;

    @GetMapping("/selectByPage")
    public R<IPage<Wallpaper>> selectByPage(PageParam param) {
        IPage iPage = PageMethods.TransportPage(param);
        IPage<Wallpaper> page = wallpaperService.selectByPage(iPage, param);
        return R.success(page);
    }

}
