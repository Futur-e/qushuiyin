package com.example.huafeng_serve.common.parseVideo.quanmin;

import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.HttpClientUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuanMinUtil {
    public static Watermark download(String url) throws IOException {
        Watermark watermark = new Watermark();

        String text = HttpClientUtil.doGet(url);
        Pattern pattern = Pattern.compile("<script type='text/javascript' >window\\.\\_\\_DATA\\_\\_ = (\\{.*?\\}); <\\/script>");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String matchedData = matcher.group(1);
            String details = String.valueOf(JsonToMapUtil.toMap(matchedData).get("detail"));
            Map<String, Object> detailsMap = JsonToMapUtil.toMap(details);
            watermark.setType(1);
            watermark.setCover((String)detailsMap.get("cover"));
            watermark.setContent((String)detailsMap.get("content"));

            String video = (String)detailsMap.get("playurl_video");
            watermark.setSourceURL(video);
        }
        return watermark;
    }
}
