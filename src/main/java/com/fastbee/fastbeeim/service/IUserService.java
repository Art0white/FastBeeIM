package com.fastbee.fastbeeim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastbee.fastbeeim.pojo.RespBean;
import com.fastbee.fastbeeim.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService extends IService<User> {
    RespBean login(String username, String password, HttpServletRequest request);

    User getUserByUserName(String username);

    List<User> getAllUsers(String keywords);
}
