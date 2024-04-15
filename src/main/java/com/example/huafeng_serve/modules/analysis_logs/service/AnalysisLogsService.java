package com.example.huafeng_serve.modules.analysis_logs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.vo.AnalysisLogsParam;

import java.util.List;


public interface AnalysisLogsService extends IService<AnalysisLogs> {
    IPage<AnalysisLogs> selectByPage(IPage<AnalysisLogs> iPage, AnalysisLogsParam analysisLogsParam);
}
