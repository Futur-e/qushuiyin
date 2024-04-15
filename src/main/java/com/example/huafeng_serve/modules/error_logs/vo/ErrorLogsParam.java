package com.example.huafeng_serve.modules.error_logs.vo;

import com.example.huafeng_serve.common.utils.PageUtil;
import lombok.Data;

@Data
public class ErrorLogsParam extends PageUtil {
    // 用户Id
    private Integer userId;
    // 错误的IP
    private String url;
    // 0-接口 1-IP错误
    private Integer type;
    // 0-图片 1-视频
    private Integer analysisType;
    // 创建时间开始 - 结束
    private String createdStartTime;
    private String createdEndTime;
}
