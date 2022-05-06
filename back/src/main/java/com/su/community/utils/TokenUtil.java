package com.su.community.utils;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.su.community.pojo.User;

import com.auth0.jwt.JWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtil {
    private static final long EXPIRE_TIME= 10*60*60*1000;
    private static final String TOKEN_SECRET="HuangTaoHTMALL";  //密钥盐

    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("uId", user.getId())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token, HttpServletRequest request){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("UID: " + jwt.getClaim("uId").asLong());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            request.getSession().setAttribute("UID",jwt.getClaim("uId").asLong());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
