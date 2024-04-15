package com.example.huafeng_serve.modules.binding_ip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.PageUtil;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.service.BindingIpService;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpParam;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bindingIp")
public class BindingIpController {

    @Autowired
    private BindingIpService bindingIpService;

    @GetMapping("/selectByPage")
    public R<IPage<BindingIp>> selectByPage(BindingIpParam param) {
        IPage iPage = PageMethods.TransportPage(param);
        IPage<BindingIp> page = bindingIpService.selectByPage(iPage, param);
        return R.success(page);
    }
    @GetMapping("/list")
    public R<List<BindingIp>> selectList() {
        List<BindingIp> list = bindingIpService.selectList();
        return R.success(list);
    }
    @GetMapping("/listToHttps")
    public R<List<BindingIp>> selectListToHttps() {
        List<BindingIp> list = bindingIpService.selectListToHttps();
        return R.success(list);
    }
    @GetMapping("/getStatistics")
    public R<BindingIpStatistics> selectStatistics(){
        BindingIpStatistics analysisLogsStatistics = bindingIpService.selectStatistics();
        return R.success(analysisLogsStatistics);
    }
    @GetMapping("/getSynchronousIp")
    public R<List<BindingIp>> selectSynchronousIp() {
        List<BindingIp> list = bindingIpService.selectSynchronousIp();
        return R.success(list);
    }
    @PostMapping("/updateBindingIp")
    public R<BindingIp> updateBindingIp(@RequestBody BindingIp bindingIp) {
        BindingIp item = bindingIpService.updateBindingIp(bindingIp);
        return R.success(item);
    }

}
