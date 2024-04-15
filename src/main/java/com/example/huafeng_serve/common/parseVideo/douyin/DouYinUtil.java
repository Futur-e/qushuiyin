package com.example.huafeng_serve.common.parseVideo.douyin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.HttpClientUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.common.utils.exception.CustomException;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;
import org.jsoup.Jsoup;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class DouYinUtil {

    public static Watermark downLoad (String url) throws IOException {
        Watermark watermark = new Watermark();
        // 获取短连接码
        String sub = "https://v.douyin.com/"+url.split("https://v.douyin.com/")[1].substring(0, 8);
        // 通过短连接获取长链接
        String redirectUrl = Jsoup.connect(sub).followRedirects(true).execute().url().toString();
        int startIndex = 0; 
        // 获取itemId
        if(redirectUrl.contains("note")) {
            startIndex = redirectUrl.indexOf("note/") + "note/".length();
            watermark.setType(0);
        }else if (redirectUrl.contains("video")) {
            startIndex = redirectUrl.indexOf("video/") + "video/".length();
            watermark.setType(1);
        }
        int endIndex = redirectUrl.indexOf("?", startIndex); // 找到第一个斜杠 "/" 的位置
        String itemId = redirectUrl.substring(startIndex, endIndex);
        String xBogusUrl = "https://tiktok.iculture.cc/X-Bogus";
        Map<String, Object> xBogusMap = new HashMap<>();
        xBogusMap.put("url","https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id="+itemId+"&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333");
        xBogusMap.put("user_agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        JSONObject xBogusData = new JSONObject(xBogusMap);
        JSONObject xBogusJson =  JSONObject.parseObject(HttpClientUtil.doPostJson(xBogusUrl,null, xBogusData.toString()));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        dataMap.put("Referer","https://www.douyin.com/");
        dataMap.put("Cookie","d_ticket=891330ffc45ead2e086d28ea83a43c2d79bfc; n_mh=QoSyK66i_Chp71SGTbf-SJk085HbULfnfHr5jZHEyOs; _bd_ticket_crypt_doamin=2; __security_server_data_status=1; passport_assist_user=Cjw290VNz5P49RdNQRbWA_Eox0af0ncPTGWMhqvFw3UWvh1EJWTMApTkXQpIn6MM3VT2MsQHyHowRNMjs0IaSgo8UertsiYtEF5dFVxpd9x_2vv3SDsuoc5Ig5-5jVgMJT7WO9RXrb5k_0Fj-5WeTPDXdrkCvLGtyAdal2DMEPr9vQ0Yia_WVCABIgEDvBmurw%3D%3D; sso_uid_tt=d0454d1d13c67fdfd6f318c3d158a706; sso_uid_tt_ss=d0454d1d13c67fdfd6f318c3d158a706; toutiao_sso_user=893f07eb14882490c9b167e17327db42; toutiao_sso_user_ss=893f07eb14882490c9b167e17327db42; _bd_ticket_crypt_cookie=41bbf8a96e6fbe926aef5603e3c93b73; LOGIN_STATUS=1; store-region=cn-tj; store-region-src=uid; sid_ucp_v1=1.0.0-KGUwNTk2NWE2ZmFiMWY1OWQwYzZhYTQwYjYwOGE1OTM5NTE1YTk1OGYKGQjN-7G15wIQjN-NqQYY2hYgDDgGQPQHSAQaAmxmIiA2MjMwYWIyMWY1ZjA5NjFjZmZiOTAyNjMwZWRiMDQxNw; ssid_ucp_v1=1.0.0-KGUwNTk2NWE2ZmFiMWY1OWQwYzZhYTQwYjYwOGE1OTM5NTE1YTk1OGYKGQjN-7G15wIQjN-NqQYY2hYgDDgGQPQHSAQaAmxmIiA2MjMwYWIyMWY1ZjA5NjFjZmZiOTAyNjMwZWRiMDQxNw; ttwid=1%7Ct82lhaKgIh-EnqchT9Z7EIAfo4zv-D2vmyVvEmddPiU%7C1696823919%7C983c7d847c2ec4de45420d24bce863fc14eb54fadea4099a0013c6af09ae6177; odin_tt=ad1a2af54d5519be45f8a9bfc3804b390f89f9c0574087db181f2670ba2efa9357ab9c98f5b1807ca758256549b92607; sid_ucp_sso_v1=1.0.0-KDgxNTBjMTQyOWZlY2UzZjJkNDU2YjA3MjYwM2JjNTNmMjcyYzlhMWUKHQjN-7G15wIQ-MWBqgYY2hYgDDCTxorWBTgGQPQHGgJsZiIgODkzZjA3ZWIxNDg4MjQ5MGM5YjE2N2UxNzMyN2RiNDI; ssid_ucp_sso_v1=1.0.0-KDgxNTBjMTQyOWZlY2UzZjJkNDU2YjA3MjYwM2JjNTNmMjcyYzlhMWUKHQjN-7G15wIQ-MWBqgYY2hYgDDCTxorWBTgGQPQHGgJsZiIgODkzZjA3ZWIxNDg4MjQ5MGM5YjE2N2UxNzMyN2RiNDI; sid_guard=893f07eb14882490c9b167e17327db42%7C1698718457%7C5184000%7CSat%2C+30-Dec-2023+02%3A14%3A17+GMT; uid_tt=d0454d1d13c67fdfd6f318c3d158a706; uid_tt_ss=d0454d1d13c67fdfd6f318c3d158a706; sid_tt=893f07eb14882490c9b167e17327db42; sessionid=893f07eb14882490c9b167e17327db42; sessionid_ss=893f07eb14882490c9b167e17327db42; home_can_add_dy_2_desktop=%220%22; dy_swidth=1920; dy_sheight=1080; strategyABtestKey=%221700547675.917%22; s_v_web_id=verify_lp7y6gky_UBZDLoVu_xFUZ_4zkO_8Axt_k9EMFWDEq3Hl; passport_csrf_token=fd9f752185a6c9bda6a68e1438a6872f; passport_csrf_token_default=fd9f752185a6c9bda6a68e1438a6872f; msToken=_Dc6lJHA5u5budAfUfhzPj9ORZ1tmYRPJaoQ46SIXYV6XrxzUeVZOShz9uhOpxfGKU00BJuC8eMudCRhNgEyrGX_tHMwlMMaDR1en1_df17q2WgOz3M8O2zZAA==; VIDEO_FILTER_MEMO_SELECT=%7B%22expireTime%22%3A1701153708261%2C%22type%22%3A1%7D; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A0%7D%22; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.826%7D; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCQUg2VHFqbjlMTEVoT0NhODFkc1ZBNVlYNXJyZnlJNmtaTWFRWk1BcW04UzAvRkhuaWhLYlhTU2NhcmtZSjFvSEswQithWEJGdEp1cTdLcmR1VWpPL2s9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoxfQ%3D%3D; __ac_signature=_02B4Z6wo00f01VU1PGgAAIDAoErW9QJvD91VETjAADAUe6qRYE8wvaJB8BfnGpjs3rKoo4hbpiV47Du45eShnrL0BUnb3vj9El5F0KpxnK8s1yByL.G2wlPH45VFCUZZLE50R2lvy3.3dIwo16; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1920%2C%5C%22screen_height%5C%22%3A1080%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A12%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A8.15%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A50%7D%22; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAANe4BjpDZCHg6Iw9FA5Kf_fSOW56JNJSO2bzMXnnVuRs%2F1700582400000%2F0%2F1700553168440%2F0%22;msToken="+getMsToken());

        JSONObject param = JSONObject.parseObject(HttpClientUtil.doPostJson(xBogusJson.get("param").toString(), dataMap, dataMap.toString()));
        if(param.getJSONObject("aweme_detail") ==null){
            throw new CustomException("该作品已被删除");
        }

        // 封面
        String cover = param.getJSONObject("aweme_detail").getJSONObject("video").getJSONObject("origin_cover").getJSONArray("url_list").toArray()[0].toString();
        // 标题
        String title = param.getJSONObject("aweme_detail").get("desc").toString();

        if(redirectUrl.contains("note")) {
            // 图文
            JSONArray images = param.getJSONObject("aweme_detail").getJSONArray("images");
            List<String> sourceList = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                String url_list = images.getJSONObject(i).getJSONArray("url_list").toArray()[0].toString();
                sourceList.add(url_list);
            }
            watermark.setSourceImgUrls(sourceList);
        }else if (redirectUrl.contains("video")) {
            // 视频
            String video = param.getJSONObject("aweme_detail").getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").toArray()[0].toString();
            watermark.setSourceURL(video);
        }
        watermark.setTitle(title);
        watermark.setCover(cover);
        return watermark;
    }

    private static String getMsToken() {
        int length = 107; // 指定生成的字符串长度
        // 字符串中包含的字符集合
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // 创建一个 StringBuilder 对象以存储生成的字符串
        StringBuilder sb = new StringBuilder(length);
        // 创建一个 Random 对象
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 从字符集合中随机选择一个字符并将其添加到 StringBuilder 中
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        // 输出生成的字符串
        String msToken = sb.toString();
        return  msToken;
    }
}
