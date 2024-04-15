package com.example.huafeng_serve.modules.invitation_logs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.invitation_logs.entity.InvitationLogs;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserParam;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserVo;

import java.util.List;

public interface InvitationLogsService extends IService<InvitationLogs> {
    Integer getUserInvitationCount(String userId);

    IPage<InvitationUserVo> selectByPage(IPage<InvitationUserVo> iPage,InvitationUserParam invitationUserParam);
}
