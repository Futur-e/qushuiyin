package com.example.huafeng_serve.modules.analysis_logs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.service.AnalysisLogsService;
import com.example.huafeng_serve.modules.analysis_logs.vo.AnalysisLogsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/logs")
public class AnalysisLogsController {
    @Autowired
    private AnalysisLogsService platformService;

    @GetMapping("/selectByPage")
    public R<IPage<AnalysisLogs>> selectUserList(AnalysisLogsParam analysisLogsParam) {
        IPage iPage = PageMethods.TransportPage(analysisLogsParam);
        IPage<AnalysisLogs> page = platformService.selectByPage(iPage,analysisLogsParam);
        return R.success(page);
    }
}
