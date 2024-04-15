package com.example.huafeng_serve.common.parseVideo.pipixia;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.huafeng_serve.common.utils.Base64Util;
import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PiPiXiaUtil {
    public static Watermark download(String url) throws IOException {
        Watermark watermark = new Watermark();
        // 重定向地址
        String s = Jsoup.connect(url).execute().url().toString();
//        String newUrl = "https://h5.pipix.com/bds/webapi/item/detail/?item_id="+s.split("https://h5.pipix.com/item/")[1].substring(0, 19);
        String newUrl = "https://is.snssdk.com/bds/cell/detail/?cell_type=1&aid=1319&app_name=super&cell_id="+s.split("https://h5.pipix.com/item/")[1].substring(0, 19);
        // 获取新地址
        String body = Jsoup.connect(newUrl).ignoreContentType(true).execute().body();

        String[] items = {"data","data","item"};
        Map<String,String> itemMaps = (Map<String,String>)JsonToMapUtil.toMaps(body, items);
        watermark.setContent(itemMaps.get("content"));
        String noteBody = String.valueOf(itemMaps.get("note"));
        String videoBody = String.valueOf(itemMaps.get("origin_video_download"));
        String coverBody = String.valueOf(itemMaps.get("cover"));

        if (coverBody != "null") {
            // 视频封面
            Map<String, Object> coverMaps = JsonToMapUtil.toMap(coverBody);
            JSONArray download_list = JSON.parseArray(String.valueOf(coverMaps.get("download_list")));
            Map<String, Object> coverUrlMaps = JsonToMapUtil.toMap(String.valueOf(download_list.get(0)));
            watermark.setCover((String) coverUrlMaps.get("url"));
        }
        if(noteBody != "null"){
            watermark.setType(0);
            Map<String, Object> noteMaps = JsonToMapUtil.toMap(noteBody);
            JSONArray multiImageList = JSON.parseArray(String.valueOf(noteMaps.get("multi_image")));
            List<String> sourceList = new ArrayList<>();
            for (int i = 0; i < multiImageList.size(); i++) {
                Map<String, Object> multiImageMaps = JsonToMapUtil.toMap(String.valueOf(multiImageList.get(i)));
                JSONArray download_list = JSON.parseArray(String.valueOf(multiImageMaps.get("download_list")));
                Map<String, Object> imagesMaps = JsonToMapUtil.toMap(String.valueOf(download_list.get(0)));
                String image = (String) imagesMaps.get("url");
                sourceList.add(image);
            }
            watermark.setSourceImgUrls(sourceList);
        }
        if(videoBody != "null") {
            watermark.setType(1);
            // 视频
            Map<String, Object> videoMaps = JsonToMapUtil.toMap(videoBody);
            JSONArray url_list = JSON.parseArray(String.valueOf(videoMaps.get("url_list")));
            Map<String, Object> videoUrlMaps = JsonToMapUtil.toMap(String.valueOf(url_list.get(0)));
            String video = (String) videoUrlMaps.get("url");
            watermark.setSourceURL(video);
        }
        return watermark;
    }
}
