package com.example.huafeng_serve.common.parseVideo.weishi;

import com.alibaba.fastjson.JSONArray;
import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

public class WeiShiUtil {
    public static Watermark download(String url) throws IOException {
        Watermark watermark = new Watermark();

        url = "https://video.weishi.qq.com"+url.split("https://video.weishi.qq.com")[1];
        url = Jsoup.connect(url).execute().url().toString();
        String id = url.split("&id=")[1].substring(0,17);
        String newUrl = "https://h5.weishi.qq.com/webapp/json/weishi/WSH5GetPlayPage?t=0.7532600494918984&g_tk=&feedid="+id+"&recommendtype=0&datalvl=&qua=&uin=&format=json&inCharset=utf-8&outCharset=utf-8";
        String body = Jsoup.connect(newUrl).ignoreContentType(true).ignoreContentType(true).followRedirects(true).execute().body();
        String data = String.valueOf(JsonToMapUtil.toMap(body).get("data"));
        List list = (List) JSONArray.parse(String.valueOf(JsonToMapUtil.toMap(data).get("feeds")));
        String feedsItemOne = String.valueOf(list.get(0));
        //视频
        String video_spec_urls = String.valueOf(JsonToMapUtil.toMap(feedsItemOne).get("video_spec_urls"));
        String zore1 = String.valueOf(JsonToMapUtil.toMap(video_spec_urls).get("0"));
        String videoUrl = String.valueOf(JsonToMapUtil.toMap(zore1).get("url"));
        // 封面
        List covers = (List) JSONArray.parse( String.valueOf(JsonToMapUtil.toMap(String.valueOf(list.get(0))).get("images")));
        String cover = String.valueOf(JsonToMapUtil.toMap(String.valueOf(covers.get(0))).get("url"));
        //内容
        String title = (String)JsonToMapUtil.toMap(feedsItemOne).get("feed_desc_withat");
        watermark.setTitle(title);
        watermark.setCover(cover);
        watermark.setType(1);
        watermark.setSourceURL(videoUrl);
//        watermark.setUrl(DownloadUtil.downLoad(videoUrl,watermark));
        return watermark;
    }
}
