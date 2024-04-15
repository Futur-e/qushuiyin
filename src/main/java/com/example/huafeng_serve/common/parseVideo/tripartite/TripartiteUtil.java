package com.example.huafeng_serve.common.parseVideo.tripartite;

import com.alibaba.fastjson.JSONArray;
import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.HttpClientUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TripartiteUtil {
    // QQ群免费API
    public static Watermark download(String url) throws IOException {
        Watermark watermark = new Watermark();
        String api = "https://yuanxiapi.cn/api/jiexi_video?url=" + url;
        String content = HttpClientUtil.doGet(api);
        if(content!="null" && content!=null){

            Map<String, Object> contentMaps = JsonToMapUtil.toMap(content);
            String code =String.valueOf(contentMaps.get("code"));
            if(code.equals("200")) {
                String type =String.valueOf(contentMaps.get("type"));
                watermark.setContent(String.valueOf(contentMaps.get("desc")));
                if(type.equals("视频")){
                    watermark.setType(1); 
                    String video = String.valueOf(contentMaps.get("video"));
                    watermark.setSourceURL(video);
                    watermark.setCover(String.valueOf(contentMaps.get("cover")));
                }else if (type.equals("图集")) {
                    watermark.setType(0);
                    JSONArray imagesArray = (JSONArray) contentMaps.get("images");
                    List<String> sourceImagesList = new ArrayList<>();
                    for (Object obj : imagesArray) {
                        if (obj instanceof String) {
                            sourceImagesList.add((String) obj);
                        }
                    }
                    watermark.setSourceImgUrls(sourceImagesList);
                }

            }
        }
        return watermark;
    }
}

