package com.example.huafeng_serve.modules.error_logs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.error_logs.entity.ErrorLogs;
import com.example.huafeng_serve.modules.error_logs.vo.ErrorLogsParam;

public interface ErrorLogsService extends IService<ErrorLogs> {
    IPage<ErrorLogs> selectByPage(IPage<ErrorLogs> iPage, ErrorLogsParam param);
    // 插入错误记录 如果是IP的情况 禁用IP 并重新转换七牛云地址
    String insertErrorLogs(ErrorLogs errorLogs);

    String deleteErrorLogs(ErrorLogs errorLogs);
}
