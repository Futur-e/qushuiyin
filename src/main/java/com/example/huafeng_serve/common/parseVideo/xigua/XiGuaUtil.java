package com.example.huafeng_serve.common.parseVideo.xigua;

import com.example.huafeng_serve.common.utils.Base64Util;
import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.HttpClientUtil;
import com.example.huafeng_serve.common.utils.JsonToMapUtil;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XiGuaUtil {
    public static Watermark download(String url) throws IOException {
        Watermark watermark = new Watermark();
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        headers.put("cookie", "passport_csrf_token=96ad7ddb4638869d792c b4a6023707f1;_ga_QEHZPBE5HH=GS1.1.1698977864.4.1.1698978193.0.0.0;ttwid=1%7CEgeelSVtKQGKVkpfJxy-A94WIglLH54oCyc5aZ1X0So%7C1698977865%7C2c2cc12fd3fa32621dfe17c103ebf47265917e10f482d7399ba4cb19f04ee4e8;support_avif=false;passport_csrf_token=e95cccca3864ac6afd0d3c749c97302f;ttwid=1%7CEgeelSVtKQGKVkpfJxy-A94WIglLH54oCyc5aZ1X0So%7C1698977865%7C2c2cc12fd3fa32621dfe17c103ebf47265917e10f482d7399ba4cb19f04ee4e8;tt_webid=7234080769935738426;msToken=oVcdOr0ctNz2WgbsdbjesSPYy4yw7SM7VZMfx4VY-dBaBnhPTRPnai3EinuEkUUa48jyd2JhlrhvXasR9PgOYZwRKf8GUDXucx7xdTOy;msToken=ThJ5jYYs9om4AmowqdYhC9wuiDUf922G6YyG8GYzGzMeLtMg8jaKM4WxVe1NTCFfY8eqsh5yPgT9d0e0AiDxam8XLwBDGsGSekObX7ZgL5-UCgncU3HA;ttwid=1%7CEgeelSVtKQGKVkpfJxy-A94WIglLH54oCyc5aZ1X0So%7C1698977865%7C2c2cc12fd3fa32621dfe17c103ebf47265917e10f482d7399ba4cb19f04ee4e8;");
        String text = HttpClientUtil.doGet(url,  null, headers);
        Map<String, String> xiGuaKeys = getXiGuaKeys(text);
        String r = Double.toString(Math.random()).substring(2);
        String vid = xiGuaKeys.get("vid");
        String d = "/video/urls/v/1/toutiao/mp4/" + vid + "?r=" + r;
        long s = encrypt(d);
        String path = "https://ib.365yg.com" + d + "&s=" + s;
        watermark.setType(1);
        watermark.setCover(xiGuaKeys.get("cover"));
        watermark.setTitle(xiGuaKeys.get("content"));
        String data = HttpClientUtil.doGet(path);
        String[] dataList = {"data","video_list","video_3","main_url"};
        String main_url = (String) JsonToMapUtil.toMaps(data, dataList);
        String video = Base64Util.decodeString(main_url);
        watermark.setSourceURL(video);
        return watermark;
    }
    private static long encrypt(String e) {
        int[] n = generateIntArray();
        int i = -1;
        int o = 0;
        int a = e.length();
        while (o < a) {
            int t = e.charAt(o++);
            if (t < 128) {
                i = (i >>> 8) ^ n[255 & (i ^ t)];
            } else if (t < 2048) {
                i =((i = (i >>> 8) ^ n[255 & (i ^ (192 | ((t >> 6) & 31)))]) >>> 8) ^n[255 & (i ^ (128 | (63 & t)))];
            } else if (t >= 55296 && t < 57344) {
                t = 64 + (1023 & t);
                int r = 1023 & e.charAt(o++);
                i =((i =((i =((i = (i >>> 8) ^ n[255 & (i ^ (240 | ((t >> 8) & 7)))]) >>>8) ^n[255 & (i ^ (128 | ((t >> 2) & 63)))]) >>>8) ^n[255 & (i ^ (128 | ((r >> 6) & 15) | ((3 & t) << 4)))]) >>>8) ^ n[255 & (i ^ (128 | (63 & r)))];
            } else {
                i =((i =((i = (i >>> 8) ^ n[255 & (i ^ (224 | ((t >> 12) & 15)))]) >>>8) ^ n[255 & (i ^ (128 | ((t >> 6) & 63)))]) >>> 8) ^  n[255 & (i ^ (128 | (63 & t)))];
            }
        }
        long s = -1 ^ i;
        return unsignedRightShift(s);
    }
private static long unsignedRightShift(long value) {
//        例如，234888539 >>> 0，如果这个值是一个正数，
//        那么结果依然是 234888539，因为正数的无符号表示和有符号表示相同。但如果这个值是一个负数，
//        将会得到其在无符号 32 位整数的二进制表示形式。
        return value & 0x00000000FFFFFFFFL;
    }
    private static int[] generateIntArray() {
        int[] t = new int[256];
        int e = 0;

        for (int n = 0; n < 256; ++n) {
            e = n;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            e = (e & 1) != 0 ? -306674912 ^ (e >>> 1) : e >>> 1;
            t[n] = e;
        }

        return t;
    }

    private static Map<String, String> getXiGuaKeys(String inputString) {
        // 使用正则表达式匹配字符串
        String regex = "<script id=\"SSR_HYDRATED_DATA\"\\s+nonce=\"[^\"]*\">window\\._SSR_HYDRATED_DATA=(\\{.*?\\})</script>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);
        // 提取匹配项
        while (matcher.find()) {
            HashMap<String, String> d = new HashMap<>();
            String hydratedData = matcher.group(1);
            String[] videos = {"anyVideo", "gidInformation", "packerData", "video"};
            String video = String.valueOf(JsonToMapUtil.toMaps(hydratedData, videos));

            d.put("vid",(String)JsonToMapUtil.toMap(video).get("vid"));
            d.put("title",(String)JsonToMapUtil.toMap(video).get("title"));
            d.put("cover",(String)JsonToMapUtil.toMap(video).get("poster_url"));
            return d;
        }
        return null;
    }
}
