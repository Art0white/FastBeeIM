package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 获取信息相关API
 * @author Lovsog
 */
@Controller
public class GetInfoController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getUserByUserName")
    public User getUserByUserName(String username) {
        return userService.getUserByUserName(username);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(String keywords) {
        System.out.println(userService.getAllUsers(keywords).toString());
        return userService.getAllUsers(keywords);
    }
}
