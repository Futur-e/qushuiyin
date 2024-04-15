package com.example.huafeng_serve.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WechatUtil {
    // 获取小程序SessionKey和OpenId
    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", "wx228c3a90f2db2d71");
        //小程序secret
        requestUrlParam.put("secret", "e591ea9794b66189f6579075252edaa9");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }
    // 获取AccessToken
    public static JSONObject getAccessToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", "wx9ccc2bc175e7dfb1");
        //小程序secret
        requestUrlParam.put("secret", "76c6d009b5549ce5c7a758007a856484");
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doGet(requestUrl, requestUrlParam,null));
        return jsonObject;
    }
}
