package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getOneUser")
    public User getUserByUserName(String username) {
        System.out.println("执行了这里, " + username);
        System.out.println(userService.getUserByUserName(username).toString());
        return userService.getUserByUserName(username);
    }

    @GetMapping("/getUserList")
    public List<User> getAllUsers(String keywords) {
        System.out.println(userService.getAllUsers(keywords).toString());
        return userService.getAllUsers(keywords);
    }

}
