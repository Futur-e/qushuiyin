package com.example.huafeng_serve.common.utils.Interceptor;

import com.example.huafeng_serve.common.utils.Base64Util;
import com.example.huafeng_serve.common.utils.exception.CustomException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 全局请求拦截器 （拦截器类）
public class SecretKeyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前进行拦截，检查是否包含Token
        String key = request.getHeader("SecretKey");
        if(key == null) {
            throw new CustomException("暂无密钥,请联系作者微信：q2637844016 获取密钥！");
        }
        String secretKey = Base64Util.decodeString(key);
        // 获取当前时间戳（秒）

        String firstFiveChars = secretKey.substring(0, 5);
        String lastFiveChars = secretKey.substring(secretKey.length() - 5);
        long currentTimestamp = System.currentTimeMillis() / 1000; // 获取当前时间戳（秒）
        long timestamp = Long.parseLong(lastFiveChars + firstFiveChars);
        // 校验时间戳是否在合理范围内    50 = 秒
        if (Math.abs(currentTimestamp - timestamp) > 50) {
            throw new CustomException("密钥校验失效,请联系作者微信：q2637844016");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后进行拦截，可以进行一些后处理操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在请求完成之后进行拦截，可以进行一些清理操作
    }
}
