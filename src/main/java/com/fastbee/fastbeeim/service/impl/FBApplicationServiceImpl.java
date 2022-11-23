package com.fastbee.fastbeeim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastbee.fastbeeim.common.GetInformationFromJWT;
import com.fastbee.fastbeeim.mapper.FBApplicationMapper;
import com.fastbee.fastbeeim.pojo.FBApplication;
import com.fastbee.fastbeeim.service.IFBApplicationService;
import com.fastbee.fastbeeim.utils.FBApplicationsUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class FBApplicationServiceImpl extends ServiceImpl<FBApplicationMapper, FBApplication> implements IFBApplicationService {

    @Resource
    private FBApplicationMapper fbApplicationMapper;

    @Resource
    private GetInformationFromJWT getInformationFromJWT;

    @Override
    public FBApplication getFBApplicationByAppId(Integer appId) {
        return fbApplicationMapper.selectOne(new QueryWrapper<FBApplication>().eq("app_id", appId).eq("enabled", true));
    }

    @Override
    public FBApplication getFBApplicationByAppName(String appName) {
        return fbApplicationMapper.selectOne(new QueryWrapper<FBApplication>().eq("app_name", appName).eq("enabled", true));
    }

    @Override
    public String getAppKey(FBApplication application) {
        return null;
    }

    @Override
    public String getAppSecret(FBApplication application) {
        return null;
    }

    @Override
    public FBApplication createApp(String appName, String secret) {
        FBApplication fbApplication = new FBApplication();
        Integer appId = fbApplicationMapper.getNextAutoIncrementOfApp();

        Map<String,String> payload = new HashMap<>();
        payload.put("appId", appId+"");
        payload.put("appName", appName);
        String appKey =  FBApplicationsUtils.createAppKey(payload, secret);

        fbApplication.setAppId(appId);
        fbApplication.setAppName(appName);
        fbApplication.setAppKey(appKey);
        fbApplication.setAppSecret(secret);
        fbApplication.setEnabled(true);

        try {
            fbApplicationMapper.insert(fbApplication);
        }catch (Exception e) {
            System.out.println("insert: " + e);
        }

        return fbApplication;
    }

    @Override
    public Integer getAppIdByAppKey(HttpServletRequest request) {
        return this.getInformationFromJWT.getAppIdByAppKey(request);
    }
}
