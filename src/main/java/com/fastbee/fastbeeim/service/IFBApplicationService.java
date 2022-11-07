package com.fastbee.fastbeeim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastbee.fastbeeim.dto.FBApplicationDTO;
import com.fastbee.fastbeeim.pojo.FBApplication;

import javax.servlet.http.HttpServletRequest;

public interface IFBApplicationService extends IService<FBApplication> {

    FBApplication getFBApplicationByAppId(Integer appId);

    FBApplication getFBApplicationByAppName(String appName);

    String getAppKey(FBApplication application);

    String getAppSecret(FBApplication application);

    FBApplication createApp(String appName, String secret);

    Integer getAppIdByAppKey(HttpServletRequest request);
}
