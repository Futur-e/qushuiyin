package com.example.huafeng_serve.modules.error_logs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.huafeng_serve.common.utils.PathUtil;
import com.example.huafeng_serve.common.utils.QiniuUtil;
import com.example.huafeng_serve.common.utils.exception.CustomException;
import com.example.huafeng_serve.modules.binding_ip.entity.BindingIp;
import com.example.huafeng_serve.modules.binding_ip.service.BindingIpService;
import com.example.huafeng_serve.modules.error_logs.entity.ErrorLogs;
import com.example.huafeng_serve.modules.error_logs.mapper.ErrorLogsMapper;
import com.example.huafeng_serve.modules.error_logs.service.ErrorLogsService;
import com.example.huafeng_serve.modules.error_logs.vo.ErrorLogsParam;
import com.example.huafeng_serve.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogsServiceImpl extends ServiceImpl<ErrorLogsMapper, ErrorLogs> implements ErrorLogsService {

    @Autowired
    private ErrorLogsMapper errorLogsMapper;

    @Autowired
    private BindingIpService bindingIpService;

    @Override
    public IPage<ErrorLogs> selectByPage(IPage<ErrorLogs> iPage, ErrorLogsParam param) {
        IPage<ErrorLogs> errorLogsIPage = errorLogsMapper.selectByPage(iPage, param);
        return errorLogsIPage;
    }

    @Override
    public String insertErrorLogs(ErrorLogs errorLogs) {
        boolean save = this.save(errorLogs);
        if (!save) {
            throw new CustomException("记录错误失败,请联系客服");
        }
        // 是IP错误  // 0-接口 1-IP错误
        if (errorLogs.getType() == 1 && errorLogs.getUrl() != null) {
            // 并且是图片 - 禁用IP
            if (errorLogs.getAnalysisType() != null &&
                    errorLogs.getAnalysisType() == 1) {
                String subUrl = PathUtil.getExtractDomain(errorLogs.getUrl());
                QueryWrapper<BindingIp> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("ip", subUrl);
                BindingIp bindingIpItems = bindingIpService.getOne(queryWrapper);
                UpdateWrapper<BindingIp> updateWrapper = new UpdateWrapper<>();
                if (bindingIpItems == null) {
                    updateWrapper.eq("ip", subUrl.replace("https://", "http://"));
                } else {
                    updateWrapper.eq("ip", subUrl);
                }
                BindingIp bindingIp = new BindingIp();
                bindingIp.setIsDisable(1);
                bindingIpService.update(bindingIp, updateWrapper);
            }
            // 并且是视频 - 重新下载
            if (errorLogs.getAnalysisType() != null &&
                    errorLogs.getAnalysisType() == 1) {
                QiniuUtil qiniuUtil = new QiniuUtil(1);
                String url = qiniuUtil.fetchFromUrl(errorLogs.getUrl());
                return url;
            }
            return "操作成功";
        }
        return "操作成功";
    }

    @Override
    public String deleteErrorLogs(ErrorLogs errorLogs) {
        boolean b = this.removeById(errorLogs.getId());
        if (b) {
            return "删除成功";
        }
        return "删除失败";
    }
}