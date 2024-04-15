package com.example.huafeng_serve.modules.invitation_logs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.invitation_logs.entity.InvitationLogs;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserParam;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface InvitationLogsMapper extends BaseMapper<InvitationLogs> {
    IPage<InvitationUserVo> selectByPage(IPage<InvitationUserVo> iPage,
                                                     @Param("param")InvitationUserParam param);
}
