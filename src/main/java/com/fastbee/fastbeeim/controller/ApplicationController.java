package com.fastbee.fastbeeim.controller;
import com.fastbee.fastbeeim.pojo.FBApplication;
import com.fastbee.fastbeeim.service.impl.FBApplicationServiceImpl;
import com.fastbee.fastbeeim.utils.RespBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 应用相关API
 * @author Lovsog
 */

@Api(tags = "应用相关API")
@RestController
@RequestMapping("/api/app")
public class ApplicationController {
    @Resource
    private FBApplicationServiceImpl fbApplicationService;

    @PostMapping("/createApp")
    public RespBean creatApplication(String appName) {
        String mySecret = System.currentTimeMillis() + "";     //当前时间戳
        FBApplication fbApplication = fbApplicationService.createApp(appName, mySecret);
        return new RespBean(200, "创建应用成功", fbApplication);
    }
}
