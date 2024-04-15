package com.example.huafeng_serve.modules.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.huafeng_serve.modules.platform.entity.Platform;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PlatformService extends IService<Platform> {

    List<Platform> selectList();
}
