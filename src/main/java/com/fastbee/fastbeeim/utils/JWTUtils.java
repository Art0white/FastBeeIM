package com.fastbee.fastbeeim.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {

    final static String mySecret = "MySecret";

    /**
     * 生成 token
     */
    public static String getToken(Map<String, String> map) {
        Date date = new Date(System.currentTimeMillis() + 86400 * 1000);
        Algorithm algorithm = Algorithm.HMAC256(mySecret);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 附带username信息
        return builder
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验 token 是否正确
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(mySecret)).build().verify(token);
    }
}