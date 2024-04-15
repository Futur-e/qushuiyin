package com.example.huafeng_serve.modules.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.common.utils.PageMethods;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.common.utils.RequestHeader;
import com.example.huafeng_serve.modules.user.entity.User;
import com.example.huafeng_serve.modules.user.service.UserService;
import com.example.huafeng_serve.modules.user.vo.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public R<List<User>> selectList() {
        List<User> users = userService.selectUserList();
        return R.success(users);
    }


    @GetMapping("/wxLogin")
    public R<User> wxLogin(String code, Integer inviterId) {
        User user = userService.wxLogin(code, inviterId);
        return R.success(user);
    }

    @GetMapping("/selectUserByCode")
    public R<User> selectUserByCode() {
        Map<String, String> headers = RequestHeader.getRequestHeaderMap();
        User user = userService.selectUserByCode(headers.get("openid"));
        return R.success(user);
    }

    @PostMapping("/updateUser")
    public R<User> updateUser(@RequestBody User user) {
        User item = userService.updateUser(user);
        if (item != null) {
            return R.success(item);
        }
        return R.error("修改失败,请重新操作！");
    }

    @PostMapping("/updateUserMemberTime")
    public R<User> updateUserMemberTime(@RequestBody User user) {
        User userInfo = userService.updateUserMemberTime(user);
        return R.success(userInfo);
    }

    @GetMapping("/selectUserByPage")
    public R<IPage<User>> selectUserByPage(UserParam userParam) {
        IPage iPage = PageMethods.TransportPage(userParam);
        IPage<User> page = userService.selectUserByPage(iPage, userParam);
        return R.success(page);
    }

    @PostMapping("/updateEvaluate")
    public R<User> updateEvaluate(@RequestBody User user) {
        User item = userService.updateEvaluate(user);
        return R.success(item);
    }
}
