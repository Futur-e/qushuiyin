package com.example.huafeng_serve.modules.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.modules.platform.entity.Platform;
import com.example.huafeng_serve.modules.platform.mapper.PlatformMapper;
import com.example.huafeng_serve.modules.platform.service.PlatformService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements PlatformService {

    @Override
    public List<Platform> selectList() {
        List<Platform> list = this.list();
        return list;
    }
}
