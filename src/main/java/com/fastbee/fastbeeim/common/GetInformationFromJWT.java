package com.fastbee.fastbeeim.common;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fastbee.fastbeeim.utils.JWTUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetInformationFromJWT {

    public int getUserIdByJWT(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        return Integer.parseInt(verify.getClaim("userId").asString());
    }

    public int getRoleIdByJWT(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        return Integer.parseInt(verify.getClaim("roleId").asString());
    }

    public String getUsernameByJWT(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        return verify.getClaim("username").asString();
    }
}