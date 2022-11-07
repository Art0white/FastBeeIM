package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.IUserService;
import com.fastbee.fastbeeim.utils.RespBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 获取信息相关API
 * @author Lovsog
 */

@Api(tags = "获取消息相关API")
@RestController
@RequestMapping("/api/info")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/getUserByUserName")
    public RespBean getUserByUserName(String username) {
        User user = userService.getUserByUserName(username);
        if(user == null) {
            return RespBean.error("不存在用户 username==" + username);
        }
        return RespBean.success("存在用户 username==" + username, user);
    }

    @PostMapping("/getUserByUserId")
    public RespBean getUserByUserId(Integer userId) {
        User user = userService.getUserByUserId(userId);
        if(user == null) {
            return RespBean.error("不存在用户 id==" + userId);
        }
        return RespBean.success("存在用户 id==" + userId, user);
    }
}
