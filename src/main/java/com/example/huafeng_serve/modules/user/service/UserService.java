package com.example.huafeng_serve.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.user.entity.User;
import com.example.huafeng_serve.modules.user.vo.UserParam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> selectUserList();

    User wxLogin(String code,Integer inviterId);

    User selectUserByCode(String openid);

    User updateUser(User user);

    User updateUserMemberTime(User param);

    IPage<User> selectUserByPage(IPage<User> iPage, UserParam userParam);

    User updateEvaluate(User param);
}
