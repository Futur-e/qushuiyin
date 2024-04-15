package com.example.huafeng_serve.modules.error_logs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.error_logs.entity.ErrorLogs;
import com.example.huafeng_serve.modules.error_logs.vo.ErrorLogsParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ErrorLogsMapper extends BaseMapper<ErrorLogs> {
    IPage<ErrorLogs> selectByPage(IPage<ErrorLogs> iPage,
                                  @Param("param") ErrorLogsParam param);
}
