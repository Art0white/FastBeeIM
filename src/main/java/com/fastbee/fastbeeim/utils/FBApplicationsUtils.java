package com.fastbee.fastbeeim.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


@Component
public class FBApplicationsUtils {
    /**
     * 生成 AppKey
     */
    public static String createAppKey(Map<String, String> map, String appSecret) {
        Date date = new Date(System.currentTimeMillis() + 86400 * 7 * 1000); // 86400L * 365 * 60 * 1000
        Algorithm algorithm = Algorithm.HMAC256(appSecret);
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
     * 校验 AppKey 是否正确
     */
    public static DecodedJWT verify(String appKey, String appSecret) {
        return JWT.require(Algorithm.HMAC256(appSecret)).build().verify(appKey);
    }
}
