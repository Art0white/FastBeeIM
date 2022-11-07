package com.fastbee.fastbeeim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastbee.fastbeeim.utils.RespBean;
import com.fastbee.fastbeeim.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserService接口
 * @author Lovsog
 */
public interface IUserService extends IService<User> {
    RespBean login(String username, String password, HttpServletRequest request);

    User getUserByUserName(String username);

    User getUserByUserId(Integer userId);

    String getPasswordByUserName(String username);
}
