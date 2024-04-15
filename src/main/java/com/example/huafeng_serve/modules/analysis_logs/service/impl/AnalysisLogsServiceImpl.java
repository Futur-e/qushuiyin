package com.example.huafeng_serve.modules.analysis_logs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.mapper.AnalysisLogsMapper;
import com.example.huafeng_serve.modules.analysis_logs.service.AnalysisLogsService;
import com.example.huafeng_serve.modules.analysis_logs.vo.AnalysisLogsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AnalysisLogsServiceImpl extends ServiceImpl<AnalysisLogsMapper, AnalysisLogs> implements AnalysisLogsService {
    @Autowired
    private AnalysisLogsMapper analysisLogsMapper;

    @Override
    public IPage<AnalysisLogs> selectByPage(IPage<AnalysisLogs> iPage, AnalysisLogsParam analysisLogsParam) {
        IPage<AnalysisLogs> page = analysisLogsMapper.selectByPage(iPage, analysisLogsParam);
        return page;
    }
}
