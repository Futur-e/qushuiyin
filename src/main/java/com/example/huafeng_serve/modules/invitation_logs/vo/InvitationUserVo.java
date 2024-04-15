package com.example.huafeng_serve.modules.invitation_logs.vo;

import com.example.huafeng_serve.modules.invitation_logs.entity.InvitationLogs;
import com.example.huafeng_serve.modules.user.entity.User;
import lombok.Data;

@Data
public class InvitationUserVo extends  InvitationLogs{

    private String nickName;

    private String openId;

    private String avatarUrl;
}
