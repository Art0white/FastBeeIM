package com.fastbee.fastbeeim.common;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fastbee.fastbeeim.utils.FBApplicationsUtils;
import com.fastbee.fastbeeim.utils.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中令牌
        RespBean respBean = null;
        String appKey = request.getHeader("appKey");
        String appSecret = request.getHeader("appSecret");
        try {
            FBApplicationsUtils.verify(appKey, appSecret);
            return true;
        }catch (SignatureVerificationException e){
            respBean = RespBean.JWTError("无效签名", false);
        }catch (TokenExpiredException e){
            respBean = RespBean.JWTError("appKey过期", false);
        }catch (AlgorithmMismatchException e){
            respBean = RespBean.JWTError("appKey算法不一致", false);
        }catch (Exception e){
            respBean = RespBean.JWTError("appKey无效", false);
        }

        String json = new ObjectMapper().writeValueAsString(respBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}