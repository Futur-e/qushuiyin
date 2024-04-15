package com.example.huafeng_serve.modules.binding_ip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BindingIpMapper extends BaseMapper<BindingIp> {
    IPage<BindingIp> selectByPage(IPage<BindingIp> iPage,
                                  @Param("param") BindingIpParam param);
}
