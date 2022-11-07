package com.fastbee.fastbeeim.common;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fastbee.fastbeeim.utils.FBApplicationsUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetInformationFromJWT {

    public Integer getAppIdByAppKey(HttpServletRequest request){
        String appKey = request.getHeader("appKey");
        String appSecret = request.getHeader("appSecret");
        DecodedJWT verify = null;
        try {
            verify = FBApplicationsUtils.verify(appKey, appSecret);
        }catch (Exception e) {
            System.out.println(e);
        }
        return Integer.parseInt(verify.getClaim("appId").asString());
    }
}