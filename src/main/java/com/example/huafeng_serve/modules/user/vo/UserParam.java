package com.example.huafeng_serve.modules.user.vo;

import com.example.huafeng_serve.common.utils.PageUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserParam extends PageUtil {
    private Integer userId;

    private String nickName;

    // 是否评价 0-未评价 1-已评价
    private Integer evaluate;
    // 创建时间开始 - 结束
    private String createdStartTime;
    private String createdEndTime;
    // 最后登录时间开始 - 结束
    private String updatedStartTime;
    private String updatedEndTime;

}
