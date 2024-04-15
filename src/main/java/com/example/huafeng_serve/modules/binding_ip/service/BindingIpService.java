package com.example.huafeng_serve.modules.binding_ip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.common.utils.PageUtil;
import com.example.huafeng_serve.modules.analysis_logs.vo.AnalysisLogsParam;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpParam;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BindingIpService extends IService<BindingIp> {
    // 分页
    IPage<BindingIp> selectByPage(IPage<BindingIp> iPage, BindingIpParam param);
    // 全部
    List<BindingIp> selectList();
    // 全部IP HTTP转HTTPS
    List<BindingIp> selectListToHttps();
    // 同步IP
    List<BindingIp> selectSynchronousIp();
    // 统计没有在IP表里的IP
    BindingIpStatistics selectStatistics();
    // 修改IP
    BindingIp updateBindingIp(BindingIp bindingIp);
}
