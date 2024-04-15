package com.example.huafeng_serve.common.parseVideo.bilibili;

import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BiliBiliUtil {
    public static Watermark downLoad (String url) throws IOException {
        Watermark watermark = new Watermark();
        String s = Jsoup.connect(url).execute().url().toString();
//        https://b23.tv/I7kHTVr

//        https://api.bilibili.com/x/player/pagelist?bvid=BV18C4y1c7wp
        //        https://api.bilibili.com/x/player/palayurl?cid=1355371101&bvid=BV18C4y1c7wp
//        https://api.bilibili.com/x/player/playurl?cid=1355371101&bvid=BV18C4y1c7wp&qn=112&otype=json&fourk=1&fnever&type=&fnval=16
//        String url3 = "https://upos-sz-estgoss.bilivideo.com/upgcxcode/01/11/1355371101/1355371101-1-100113.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEV4NC8xNEV4N03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&uipk=5&nbs=1&deadline=1703150085&gen=playurlv2&os=upos&oi=1872810018&trid=f20770f4322049ad83f1329274d83badu&mid=256321436&platform=pc&upsig=bf9090ff76ab4edc8a7cdeacaf73220e&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&orderid=1,3&buvid=9C20D9FD-008F-4237-A885-338D3471C87D167625infoc&build=0&f=u_0_0&agrr=1&bw=125270&logo=40000000";
//        watermark.setUrl(DownloadUtil.downLoad(url3,watermark));
        //  https://api.bilibili.com/x/web-interface/view?bvid=BV18C4y1c7wp
//        https://api.bilibili.com/x/player/playurl?cid=1355371101&bvid=BV18C4y1c7wp&qn=112&otype=json&fourk=1&fnever&type=&fnval=16
        return watermark;
    }
}
