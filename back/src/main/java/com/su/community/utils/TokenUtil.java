package com.su.community.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.su.community.pojo.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenUtil {
    private static final String TOKEN_SECRET = "SUZJ";  //密钥盐

    /**
     * 签名生成
     *
     * @param user
     * @return
     */
    public static String sign(User user) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("sub")
                    .withSubject(user.getId().toString())
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("sub").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从request中获取用户名
     *
     * @param request
     * @return
     */
    public Long getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader("token");
        return token == null ? null : getUserIdFromToken(token);
    }

    /**
     * 从token中获取用户id
     *
     * @param token
     * @return
     */
    public Long getUserIdFromToken(String token) {
        String subject = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("sub").build().verify(token).getSubject();
        return Long.parseLong(subject);
    }

}
