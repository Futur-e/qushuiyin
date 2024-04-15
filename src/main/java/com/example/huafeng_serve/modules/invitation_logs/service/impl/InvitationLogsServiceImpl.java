package com.example.huafeng_serve.modules.invitation_logs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.modules.invitation_logs.entity.InvitationLogs;
import com.example.huafeng_serve.modules.invitation_logs.mapper.InvitationLogsMapper;
import com.example.huafeng_serve.modules.invitation_logs.service.InvitationLogsService;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserParam;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InvitationLogsServiceImpl extends ServiceImpl<InvitationLogsMapper, InvitationLogs> implements InvitationLogsService {
    @Autowired
    private InvitationLogsMapper invitationLogsMapper;
    @Override
    public Integer getUserInvitationCount(String userId) {
        Integer count = this.count(new QueryWrapper<InvitationLogs>().eq("inviter_id", userId));
        return count;
    }

    @Override
    public IPage<InvitationUserVo> selectByPage(IPage<InvitationUserVo> iPage,InvitationUserParam invitationUserParam) {
        IPage<InvitationUserVo> page = invitationLogsMapper.selectByPage(iPage, invitationUserParam);
        return page;
    }
}
