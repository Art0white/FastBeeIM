package com.fastbee.fastbeeim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastbee.fastbeeim.mapper.UserMapper;
import com.fastbee.fastbeeim.utils.RespBean;
import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.IUserService;
import com.fastbee.fastbeeim.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    // ↑ ↑ ↑ 这里报错是idea显示问题, 不影响使用 ↑ ↑ ↑

    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        return null;
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("enabled", true));
    }

    @Override
    public List<User> getAllUsers(String keywords) {
        return null;
        //userMapper.getAllUsers(UserUtils.getCurrentUser().getId(), keywords)
    }
}
