package com.example.huafeng_serve.modules.invitation_logs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.service.AnalysisLogsService;
import com.example.huafeng_serve.modules.invitation_logs.service.InvitationLogsService;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserParam;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/invitationLogs")
public class InvitationLogsController {
    @Autowired
    private InvitationLogsService invitationLogsService;

    @GetMapping("/getCount")
    public R<Integer> selectUserInvitationCount(@RequestParam String userId) {
        Integer count = invitationLogsService.getUserInvitationCount(userId);
        return R.success(count);
    }

    @GetMapping("/getList")
    public R<IPage<InvitationUserVo>> selectUserInvitationList(InvitationUserParam invitationUserParam) {
        IPage iPage = PageMethods.TransportPage(invitationUserParam);
        IPage<InvitationUserVo> page = invitationLogsService.selectByPage(iPage, invitationUserParam);
        return R.success(page);
    }
}
