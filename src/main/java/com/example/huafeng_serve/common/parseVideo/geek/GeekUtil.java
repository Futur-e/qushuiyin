package com.example.huafeng_serve.common.parseVideo.geek;

import com.alibaba.fastjson.JSONArray;
import com.example.huafeng_serve.common.utils.HttpClientUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.common.utils.Verify;
import com.example.huafeng_serve.common.utils.exception.CustomException;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeekUtil {
    public static Watermark download(String url) throws IOException {
        Watermark watermark = new Watermark();
        String api = "https://api.wxshares.com/api/qsy/as?key=X3OJll9qVjHEhUrAHybLWJ84Ht&url=" + url;
        String content = HttpClientUtil.doGet(api);
        if(content!="null" && content!=null) {
            Map<String, Object> contentMaps = JsonToMapUtil.toMap(content);
            String code =String.valueOf(contentMaps.get("code"));
            if(code.equals("200")) {
                Map<String, Object> data = JsonToMapUtil.toMap(String.valueOf(contentMaps.get("data")));
                String dataUrl = String.valueOf(data.get("url"));
                watermark.setTitle(String.valueOf(data.get("title")));
                watermark.setCover(String.valueOf(data.get("photo")));

                if(Verify.check(String.valueOf(data.get("pics")))){
                    watermark.setType(0);
                    JSONArray imagesArray = (JSONArray) data.get("pics");
                    List<String> sourceImagesList = new ArrayList<>();
                    for (Object obj : imagesArray) {
                        if (obj instanceof String) {
                            sourceImagesList.add((String) obj);
                        }
                    }
                    watermark.setSourceImgUrls(sourceImagesList);
                }else if (Verify.check(dataUrl)) {
                    watermark.setType(1);
                    watermark.setSourceURL(dataUrl);
                }else {
                    throw new CustomException("小的无能此链接俺没有解析出来!~!，换一条来试试吧！");
                }
            }
        }
        return watermark;
    }
}
