package com.example.huafeng_serve.modules.watermark.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.huafeng_serve.common.parseVideo.bilibili.BiliBiliUtil;
import com.example.huafeng_serve.common.parseVideo.douyin.DouYinUtil;
import com.example.huafeng_serve.common.parseVideo.geek.GeekUtil;
import com.example.huafeng_serve.common.parseVideo.kuaishou.KuaiShouUtil;
import com.example.huafeng_serve.common.parseVideo.pipixia.PiPiXiaUtil;
import com.example.huafeng_serve.common.parseVideo.quanmin.QuanMinUtil;
import com.example.huafeng_serve.common.parseVideo.tripartite.TripartiteUtil;
import com.example.huafeng_serve.common.parseVideo.weishi.WeiShiUtil;
import com.example.huafeng_serve.common.parseVideo.xiaohongshu.XiaoHongShuUtil;
import com.example.huafeng_serve.common.parseVideo.xigua.XiGuaUtil;
import com.example.huafeng_serve.common.utils.DownloadUtil;
import com.example.huafeng_serve.common.utils.QiniuUtil;
import com.example.huafeng_serve.common.utils.R;
import com.example.huafeng_serve.common.utils.RequestHeader;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.service.AnalysisLogsService;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.service.BindingIpService;
import com.example.huafeng_serve.modules.user.entity.User;
import com.example.huafeng_serve.modules.user.service.UserService;
import com.example.huafeng_serve.modules.watermark.entity.Watermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(value = "/watermark")
public class WatermarkController {
    @Autowired
    private UserService userService;
    @Autowired
    private AnalysisLogsService analysisLogsService;
    @Autowired
    private BindingIpService bindingIpService;

    @GetMapping
    public R<Watermark> watermark(String url) {
        try {
            if (url == null) {
                return R.error("未检测到路径");
            }

            // 计算累计用户去掉的水印次数
            Map<String, String> headers = RequestHeader.getRequestHeaderMap();
            User userInfo = userService.selectUserByCode(headers.get("openid"));


            Watermark watermark = null;
            if (url.contains("https://v.douyin.com/")) {
                watermark = GeekUtil.download(url);
                // watermark = DouYinUtil.downLoad(url);
                watermark.setPlatform("抖音");
            } else if (url.contains("https://v.kuaishou.com/") || url.contains("http://o.m.chenzhongtech.com")) {
//                watermark = TripartiteUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("快手");
            } else if (url.contains("https://h5.pipix.com")) {
//                watermark = PiPiXiaUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("皮皮虾");
            } else if (url.contains("https://video.weishi.qq.com")) {
//                watermark = WeiShiUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("微视");
            } else if (url.contains("https://v.ixigua.com")) {
//                watermark = XiGuaUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("西瓜");
            } else if (url.contains("https://b23.tv")) {
//                watermark = TripartiteUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("哔哩哔哩");
            } else if (url.contains("http://xhslink.com")) {
//                watermark = TripartiteUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("小红书");
            } else if (url.contains("https://static-play.kg.qq.com")) {
//                watermark = QuanMinUtil.download(url);
                watermark = GeekUtil.download(url);
                watermark.setPlatform("全民K歌");
            } else if (url.contains("https://share.xiaochuankeji.cn")) {
                watermark = GeekUtil.download(url);
                watermark.setPlatform("最右");
            } else if (url.contains("https://haokan.hao123.com")) {
                watermark = GeekUtil.download(url);
                watermark.setPlatform("好看");
            } else if (url.contains("http://www.meipai.com")) {
                watermark = GeekUtil.download(url);
                watermark.setPlatform("美拍");
            } else if (url.contains("https://lv.ulikecam.com")) {
                watermark = GeekUtil.download(url);
                watermark.setPlatform("剪映");
            } else {
                watermark = GeekUtil.download(url);
                watermark.setPlatform("未记录");
            }

            if (watermark.getType() == null) {
                return R.error(watermark.getPlatform() + "正在维护中,如有问题请联系客服咨询！");
            }
            // 判断数据库是否有已经认证校验的域名了 有直接返回 没有下载
            List<BindingIp> bindingIpList = bindingIpService.selectList();
            QiniuUtil qiniuUtil = new QiniuUtil(watermark.getType());
            if (watermark.getType() == 0) {
                // 图文
                List<String> imageList = new ArrayList<>();
                for (String sourceImgUrl : watermark.getSourceImgUrls()) {
                    boolean ipExists = false;
                    for (BindingIp bindingIp : bindingIpList) {
                        if (sourceImgUrl.contains(bindingIp.getIp())) {
                            ipExists = true;
                            break;
                        }
                    }
                    if (ipExists) {
                        if (sourceImgUrl.startsWith("http://")) {
                            String replacedSourceImgUrl = sourceImgUrl.replace("http://", "https://");
                            imageList.add(replacedSourceImgUrl);
                        } else {
                            imageList.add(sourceImgUrl);
                        }

                    } else {
                        imageList.add(DownloadUtil.downLoad(sourceImgUrl,watermark));
                    }
                }
                watermark.setImgUrls(imageList);
            } else if (watermark.getType() == 1) {
                // 视频
                boolean ipExists = false;
                for (BindingIp bindingIp : bindingIpList) {
                    if (watermark.getSourceURL().contains(bindingIp.getIp())) {
                        ipExists = true;
                        break;
                    }
                }
                if (ipExists) {
                    if (watermark.getSourceURL().startsWith("http://")) {
                        String replacedSourceImgUrl = watermark.getSourceURL().replace("http://", "https://");
                        watermark.setUrl(replacedSourceImgUrl);
                    } else {
                        watermark.setUrl(watermark.getSourceURL());
                    }
                } else {
                    watermark.setUrl(DownloadUtil.downLoad(watermark.getSourceURL(),watermark));
                }
            }

            userInfo.setOperationTime(LocalDateTime.now());
            if (userInfo.getOperationNumber() == null) {
                userInfo.setOperationNumber(1);
            } else {
                userInfo.setOperationNumber(userInfo.getOperationNumber() + 1);
            }
            userService.updateById(userInfo);

            // 加入日志
            AnalysisLogs analysisLogs = new AnalysisLogs();
            analysisLogs.setUserId(userInfo.getId());
            analysisLogs.setType(watermark.getType());
            analysisLogs.setUrl(url);

            analysisLogs.setTitle(watermark.getTitle());
            analysisLogs.setContent(watermark.getContent());
            analysisLogs.setCover(watermark.getCover());
            analysisLogs.setPlatform(watermark.getPlatform());
            if (watermark.getType() == 0) {
                // 图文
                analysisLogs.setImgUrls(String.join(",", watermark.getSourceImgUrls()));
                analysisLogs.setImgDownloadUrls(String.join(",", watermark.getImgUrls()));
            } else if (watermark.getType() == 1) {
                // 视频
                analysisLogs.setVideoUrl(watermark.getSourceURL());
                analysisLogs.setVideoDownloadUrl(watermark.getUrl());
            }

            analysisLogsService.save(analysisLogs);
            return R.success(watermark);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return R.error("系统异常");
    }

    @GetMapping("verifyDownload")
    public R<Integer> verifyDownload() {
        // 状态 0-未评论 1-未观看视频 2-直接通过

        Map<String, String> headers = RequestHeader.getRequestHeaderMap();
        User user = userService.selectUserByCode(headers.get("openid"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 0 1 随机
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (user.getMemberTime() == null) {
            // 不是会员,未评价的情况下 并且从来没有使用过的情况下 (新用户)
            if ((user.getEvaluate() == null || user.getEvaluate() == 0) &&
                    user.getOperationNumber() <= 1) {
                return R.success(0);
            }
            // 不的是会员,未评价情况下 使用过 0 和 1随机
            if (user.getEvaluate() == null || user.getEvaluate() == 0) {
                return R.success(randomNumber);
            }
            return R.success(1);
        }

        if (user.getMemberTime().isBefore(currentDateTime)) {
            // 会员过期 并且未评价   0 和 1随机
            if (user.getEvaluate() == null || user.getEvaluate() == 0) {
                return R.success(randomNumber);
            }
            return R.success(1);
        }

        if (user.getMemberTime().isAfter(currentDateTime)) {
            // 是会员并且未过期并且未评论
            if (user.getEvaluate() == null || user.getEvaluate() == 0) {
                return R.success(0);
            }
        }
        return R.success(2);
    }
}
