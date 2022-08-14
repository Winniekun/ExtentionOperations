package com.wkk.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/5/1
 */
public class JWTDemo {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 20);
        String token = JWT.create()
                .withClaim("username", "wkk") // payload
                .withClaim("userId", 21) // payload
                .withExpiresAt(instance.getTime()) // 令牌过期时间
                .sign(Algorithm.HMAC256("secret_key"));// 算法

        System.out.println(token);

        //解析数据

        Algorithm algorithm = Algorithm.HMAC256("secret_key");
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        System.out.println("username: " + jwt.getClaim("username"));
        System.out.println("userId: " + jwt.getClaim("userId"));
        System.out.println("expire time" + jwt.getExpiresAt());


    }
}
