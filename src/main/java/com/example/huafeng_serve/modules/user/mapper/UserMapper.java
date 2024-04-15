package com.example.huafeng_serve.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.user.entity.User;
import com.example.huafeng_serve.modules.user.vo.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectUserByPage(IPage<User> iPage,
                                 @Param("param") UserParam userParam);
}
