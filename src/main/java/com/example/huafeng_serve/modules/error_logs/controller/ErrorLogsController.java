package com.example.huafeng_serve.modules.error_logs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.error_logs.entity.ErrorLogs;
import com.example.huafeng_serve.modules.error_logs.service.ErrorLogsService;
import com.example.huafeng_serve.modules.error_logs.vo.ErrorLogsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/errorLogs")
public class ErrorLogsController {
    @Autowired
    private ErrorLogsService errorLogsService;

    @GetMapping("/selectByPage")
    public R<IPage<ErrorLogs>> selectByPage(ErrorLogsParam param) {
        IPage iPage = PageMethods.TransportPage(param);
        IPage<ErrorLogs> page = errorLogsService.selectByPage(iPage, param);
        return R.success(page);
    }

    @PostMapping("/insert")
    public R<String> insertErrorLogs(@RequestBody  ErrorLogs param) {
        String s = errorLogsService.insertErrorLogs(param);
        return R.success(s);
    }

    @PostMapping("/delete")
    public R<String> deleteErrorLogs(@RequestBody  ErrorLogs param) {
        String s = errorLogsService.deleteErrorLogs(param);
        return R.success(s);
    }
}
