package com.example.huafeng_serve.modules.ziliao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.invitation_logs.entity.InvitationLogs;
import com.example.huafeng_serve.modules.invitation_logs.vo.InvitationUserVo;
import com.example.huafeng_serve.modules.wallpaper.vo.PageParam;
import com.example.huafeng_serve.modules.ziliao.entity.Ziliao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ZiliaoMapper extends BaseMapper<Ziliao> {
    IPage<Ziliao> selectByPage(IPage iPage, PageParam param);
}
