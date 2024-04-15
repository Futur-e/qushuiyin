package com.example.huafeng_serve.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取HTTP的请求头参数
 */
public class RequestHeader {
    //获得当前HttpServletRequest对象
    private static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    //获取heard中的参数
    public static Map<String, String> getRequestHeaderMap() {
        HttpServletRequest request = getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>(8);
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headerMap.put(name, request.getHeader(name));
        }
        return headerMap;
    }
}
