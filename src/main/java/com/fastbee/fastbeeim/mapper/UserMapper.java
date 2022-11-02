package com.fastbee.fastbeeim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastbee.fastbeeim.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> getAllUsers(@Param("id") Integer id, @Param("keywords") String keywords);
}