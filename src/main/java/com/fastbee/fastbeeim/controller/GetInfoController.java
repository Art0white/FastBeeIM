package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.IUserService;
import com.fastbee.fastbeeim.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取信息相关API
 * @author Lovsog
 */
@RestController
public class GetInfoController {
    @Autowired
    private IUserService userService;

    @PostMapping("/getUserByUserName")
    public RespBean getUserByUserName(String username) {
        User user = userService.getUserByUserName(username);
        if(user == null) {
            return RespBean.error("不存在用户 username==" + username);
        }
        return RespBean.success("存在用户 username==" + username, user);
    }

    @PostMapping("/getUserById")
    public RespBean getUserById(Integer id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return RespBean.error("不存在用户 id==" + id);
        }
        return RespBean.success("存在用户 id==" + id, user);
    }

    @PostMapping("/getAllUsers")
    public List<User> getAllUsers(String keywords) {
        System.out.println(userService.getAllUsers(keywords).toString());
        return userService.getAllUsers(keywords);
    }
}
