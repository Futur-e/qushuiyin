package com.example.huafeng_serve.modules.binding_ip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.common.utils.PageUtil;
import com.example.huafeng_serve.common.utils.PathUtil;
import com.example.huafeng_serve.common.utils.exception.CustomException;
import com.example.huafeng_serve.modules.analysis_logs.entity.AnalysisLogs;
import com.example.huafeng_serve.modules.analysis_logs.service.AnalysisLogsService;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.mapper.BindingIpMapper;
import com.example.huafeng_serve.modules.binding_ip.service.BindingIpService;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpParam;
import com.example.huafeng_serve.modules.binding_ip.vo.BindingIpStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BindingIpServiceImpl extends ServiceImpl<BindingIpMapper, BindingIp> implements BindingIpService {
    @Autowired
    private AnalysisLogsService analysisLogsService;

    @Autowired
    private BindingIpMapper bindingIpMapper;

    @Override
    public IPage<BindingIp> selectByPage(IPage<BindingIp> iPage, BindingIpParam param) {
        IPage<BindingIp> bindingIpIPage = bindingIpMapper.selectByPage(iPage, param);
        return bindingIpIPage;
    }

    @Override
    public List<BindingIp> selectList() {
        QueryWrapper<BindingIp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_disable", 0);
        List<BindingIp> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public List<BindingIp> selectListToHttps() {
        QueryWrapper<BindingIp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_disable", 0);
        List<BindingIp> list = this.list(queryWrapper);
        List<BindingIp> listToHttps= new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BindingIp bindingIp = new BindingIp();
            bindingIp.setId(list.get(i).getId());
            bindingIp.setIsDisable(list.get(i).getIsDisable());
            if(list.get(i).getIp().startsWith("http://")){
                String replacedUrl =list.get(i).getIp().replace("http://", "https://");
                bindingIp.setIp(replacedUrl);
            }else {
                bindingIp.setIp(list.get(i).getIp());
            }
            listToHttps.add(bindingIp);
        }
        return listToHttps;
    }
    @Override
    public List<BindingIp> selectSynchronousIp() {
        List<BindingIp> bindingIps = new ArrayList<>();
        BindingIpStatistics bindingIpStatistics = this.selectStatistics();
        List<String> all = bindingIpStatistics.getAll();
        for (int i = 0; i < all.size(); i++) {
            BindingIp bindingIp = new BindingIp();
            bindingIp.setIp(all.get(i));
            bindingIp.setIsDisable(0);
            bindingIps.add(bindingIp);
        }
        boolean b = this.saveBatch(bindingIps);
        if (!b) {
            throw new CustomException("同步数据失败！");
        }
        return bindingIps;
    }

    @Override
    public BindingIpStatistics selectStatistics() {
        List<AnalysisLogs> list = analysisLogsService.list();
        List<String> ips = this.list().stream().map(BindingIp::getIp)
                .collect(Collectors.toList());
        List<String> videos = new ArrayList<>();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AnalysisLogs item = list.get(i);
            if (item.getType() == 0) {
                // 图片 subImageUrl.startsWith("https://") &&
                String[] imageList = item.getImgUrls().split(",");
                for (int j = 0; j < imageList.length; j++) {
                    String subImageUrl = PathUtil.getExtractDomain(imageList[j]);
                    if (!images.contains(subImageUrl) && !ips.contains(subImageUrl)) {
                        images.add(subImageUrl);
                    }
                }
            } else if (item.getType() == 1) {
                // 视频
                String subVideoUrl = PathUtil.getExtractDomain(item.getVideoUrl());
                if (!videos.contains(subVideoUrl) && !ips.contains(subVideoUrl)) {
                    videos.add(subVideoUrl);
                }
            }
        }
        List<String> mergedList = Stream.concat(videos.stream(), images.stream())
                .distinct()
                .collect(Collectors.toList());
        BindingIpStatistics bindingIpStatistics = new BindingIpStatistics();
        bindingIpStatistics.setVideos(videos);
        bindingIpStatistics.setImages(images);
        bindingIpStatistics.setAll(mergedList);
        return bindingIpStatistics;
    }

    @Override
    public BindingIp updateBindingIp(BindingIp bindingIp) {
        boolean b = this.updateById(bindingIp);
        if (!b) {
            throw new CustomException("操作失败！");
        }
        return bindingIp;
    }
}
