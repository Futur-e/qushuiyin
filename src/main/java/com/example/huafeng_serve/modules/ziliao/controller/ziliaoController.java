package com.example.huafeng_serve.modules.ziliao.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import com.example.huafeng_serve.modules.ziliao.entity.Ziliao;
import com.example.huafeng_serve.modules.ziliao.service.ZiliaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ziliaoclass")
public class ziliaoController {

        @Autowired
        private ZiliaoService ziliaoService;

        @GetMapping("/selectByPage")
        public R<IPage<Ziliao>> selectByPage(PageParam param) {
            IPage iPage = PageMethods.TransportPage(param);
            IPage<Ziliao> page = ziliaoService.selectByPage(iPage, param);
            return R.success(page);
        }
        @PostMapping("/addziliao")
    public R<String> insertZiliao(@RequestBody Ziliao param) {
        String s = ziliaoService.insertZiliao(param);
        return R.success("插入成功");
    }
}
