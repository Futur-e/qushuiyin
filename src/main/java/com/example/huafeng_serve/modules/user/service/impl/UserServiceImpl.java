package com.example.huafeng_serve.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.common.utils.ProjectProperties;
import com.example.huafeng_serve.common.utils.WechatUtil;
import com.example.huafeng_serve.common.utils.exception.CustomException;
import com.example.huafeng_serve.modules.invitation_logs.entity.InvitationLogs;
import com.example.huafeng_serve.modules.invitation_logs.service.InvitationLogsService;
import com.example.huafeng_serve.modules.user.entity.User;
import com.example.huafeng_serve.modules.user.mapper.UserMapper;
import com.example.huafeng_serve.modules.user.service.UserService;
import com.example.huafeng_serve.modules.user.vo.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private InvitationLogsService invitationLogsService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUserList() {
        return this.list();
    }

    @Override
    public User wxLogin(String code, Integer inviterId) {
        JSONObject sessionKeyOrOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openid = new JSONObject(sessionKeyOrOpenId).getString("openid");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openid);
        User userInfo = this.getOne(queryWrapper);
        if (userInfo == null) {
            Random random = new Random();
            int randomIndex = random.nextInt(12);
            User user = new User();
            user.setOpenId(openid);
            String[] names = {
                    "碧海蓝天谷",
                    "雅韵书香居",
                    "花开富贵府",
                    "星光璀璨亭",
                    "古风雅颂屋",
                    "诗意田园府",
                    "翠影幽谷园",
                    "龙飞凤舞屋",
                    "画意青山居",
                    "文雅雅府邸",
                    "书香门第园",
                    "韵味悠长屋"
            };

            user.setNickName(names[randomIndex]);
            user.setOperationNumber(0);
            user.setEvaluate(0);
            user.setAvatarUrl(ProjectProperties.file + "default-avatar-" + randomIndex + ".png");
            boolean isSave = this.save(user);
            if (isSave && inviterId != null) {
                // 是邀请的情况下
                User inviter = this.getOne(new QueryWrapper<User>().eq("id", inviterId));
                if (inviter == null) {
                    return user;
                }
                // 添加一条邀请记录
                InvitationLogs invitationLogs = new InvitationLogs();
                invitationLogs.setUserId(user.getId());
                invitationLogs.setInviterId(inviter.getId());
                invitationLogsService.save(invitationLogs);

                int invitersNumber  = invitationLogsService.count(new QueryWrapper<InvitationLogs>().eq("inviter_id", inviterId));
                    // 如果用户邀请数量大于等于10人
                if(invitersNumber >= 10){
                    inviter.setMemberTime(LocalDateTime.of(2099, 12, 30, 23, 59));
                }else {
                    LocalDateTime memberTime = this.getMemberTime(inviter);
                    inviter.setMemberTime(memberTime);
                }
                // 修改邀请人的会员时间
                this.updateById(inviter);
                // 返回新用户
                return user;
            } else if (isSave) {
                // 新用户
                return user;
            }
        } else {
            userInfo.setUpdatedTime(LocalDateTime.now());
            this.updateById(userInfo);
        }
        return userInfo;
    }

    @Override
    public User selectUserByCode(String openid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openid);
        User userInfo = this.getOne(queryWrapper);
        if (userInfo != null) {
            return userInfo;
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        boolean isUpdate = this.updateById(user);
        if (isUpdate) {
            User item = this.getById(user.getId());
            return item;
        }
        throw new CustomException("修改数据失败！");
    }

    @Override
    public User updateUserMemberTime(User param) {
        User user = this.getById(param.getId());
        LocalDateTime memberTime = this.getMemberTime(user);
        user.setMemberTime(memberTime);
        boolean isUpdate = this.updateById(user);
        if (isUpdate) {
            return user;
        }
        throw new CustomException("修改数据失败！");
    }

    @Override
    public IPage<User> selectUserByPage(IPage<User> iPage, UserParam userParam) {
        IPage<User> userIPage = userMapper.selectUserByPage(iPage, userParam);
        return userIPage;
    }

    @Override
    public User updateEvaluate(User param) {
        User user = this.getById(param.getId());
        user.setEvaluate(param.getEvaluate());
        LocalDateTime memberTime = this.getMemberTime(user);
        user.setMemberTime(memberTime);
        boolean isUpdate = this.updateById(user);
        if (isUpdate) {
            return user;
        }
        throw new CustomException("修改数据失败！");
    }

    private LocalDateTime getMemberTime(User user){
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (user.getMemberTime() == null) {
            // 没有当过会员的情况下
            user.setMemberTime(currentDateTime.plusDays(1));
        }else if(user.getMemberTime().getYear() >= 2099) {
            // 已经是永久会员了
            user.setMemberTime(LocalDateTime.of(2099, 12, 30, 23, 59));
        }else if(user.getMemberTime().isBefore(currentDateTime)) {
            // 判断 memberTime 是否早于当前时间（已经过期）
            user.setMemberTime(currentDateTime.plusDays(1));
        }else if(user.getMemberTime().isAfter(currentDateTime)) {
            // 判断 memberTime 是否晚于当前时间（未过期）
            user.setMemberTime(user.getMemberTime().plusDays(1));
        }
        return user.getMemberTime();
    }
}
