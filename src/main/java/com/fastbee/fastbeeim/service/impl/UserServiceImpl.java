package com.fastbee.fastbeeim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastbee.fastbeeim.common.GetInformationFromJWT;
import com.fastbee.fastbeeim.mapper.UserMapper;
import com.fastbee.fastbeeim.utils.RespBean;
import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * UserService实现
 * @author Lovsog
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private GetInformationFromJWT getInformationFromJWT;

    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        return null;
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("enabled", true));
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId).eq("enabled", true));
    }

    @Override
    public String getPasswordByUserName(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("enabled", true));
        return user.getPassword();
    }
}
