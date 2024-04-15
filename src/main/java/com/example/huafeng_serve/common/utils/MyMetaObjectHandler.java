package com.example.huafeng_serve.common.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createdTime", LocalDateTime.now());
        metaObject.setValue("updatedTime",LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updatedTime",LocalDateTime.now());
    }
}