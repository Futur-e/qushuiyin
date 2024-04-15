package com.example.huafeng_serve.modules.invitation_logs.vo;

import com.example.huafeng_serve.common.utils.PageUtil;
import lombok.Data;

@Data
public class InvitationUserParam  extends PageUtil {
    // 用户Id
    private Integer userId;
    // 邀请人Id
    private Integer inviterId;

}