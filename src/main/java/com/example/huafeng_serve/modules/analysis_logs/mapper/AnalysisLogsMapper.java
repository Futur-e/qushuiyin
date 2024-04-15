package com.example.huafeng_serve.modules.analysis_logs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.vo.AnalysisLogsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnalysisLogsMapper extends BaseMapper<AnalysisLogs> {
    IPage<AnalysisLogs> selectByPage(IPage<AnalysisLogs> iPage,
                                     @Param("param") AnalysisLogsParam param);
}
