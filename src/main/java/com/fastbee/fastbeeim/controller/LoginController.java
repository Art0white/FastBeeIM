package com.fastbee.fastbeeim.controller;

import com.fastbee.fastbeeim.dto.LoginDTO;
import com.fastbee.fastbeeim.pojo.User;
import com.fastbee.fastbeeim.service.impl.UserServiceImpl;
import com.fastbee.fastbeeim.utils.RespBean;
import io.swagger.annotations.Api;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录相关API")
@RestController
@Validated
@CrossOrigin
public class LoginController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    public RespBean login(LoginDTO loginDTO, HttpServletResponse response) {
//        String salt = userService.getUserByUserName(loginDTO.getUsername());
//        String password = new SimpleHash("MD5", loginDTO.getPassword(), salt, 1).toHex();
        String password = loginDTO.getPassword();
        String realPassword = userService.getPasswordByUserName(loginDTO.getUsername());
        if(ObjectUtils.isEmpty(realPassword)){
            return new RespBean(400, "用户名错误",0);
        } else if (!realPassword.equals(password)) {
            return new RespBean(400, "密码错误", 0);
        } else {
            User user = userService.getUserByUserName(loginDTO.getUsername());
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", userService.getToken(user));
            return new RespBean(200,"success", tokenMap);
        }
    }

    @PostMapping("/mytest")
    public RespBean test(HttpServletRequest request) {
        String username = userService.getUsernameByJWT(request);
        return new RespBean(200,"success", username);
    }
}