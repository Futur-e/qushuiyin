package com.example.huafeng_serve.modules.analysis_logs.vo;

import com.example.huafeng_serve.common.utils.PageUtil;
import lombok.Data;

@Data
public class AnalysisLogsParam extends PageUtil {
    // 用户Id
    private Integer userId;

    // 解析平台
    private String platform;

    // 解析类型
    private Integer type;

    // 创建时间开始 - 结束
    private String createdStartTime;
    private String createdEndTime;
}
