package com.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类封装
 *
 * @author asus
 */
public class JwtUtils {

    public static final String SUBJECT = "congge";

    /**
     * 设置token的过期时间 有效期7天
     */
    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    /**
     * 秘钥，不同的环境应该配置不同的秘钥，注意保存好，不要泄露
     */
    public static final String APPSECRET_KEY = "congge_secret";

    /**
     * 加密生成token
     *
     * @param userContext
     * @return
     */
    public static String generateJsonWebToken(UserContext userContext) {
        if (userContext.getUserId() == null || userContext.getUserName() == null || userContext.getUserCode() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("userId", userContext.getUserId())
                .claim("userName", userContext.getUserName())
                .claim("userCode", userContext.getUserCode())
                .claim("userAuthority", userContext.getUserAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 解密token获取用户信息
     *
     * @param token
     * @return
     */
    public static UserContext analysisJWTToUserContext(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            if (claims != null) {
                UserContext userContext = new UserContext();
                userContext.setUserId(Long.valueOf(claims.get("userId").toString()));
                userContext.setUserName(claims.get("userName").toString());
                userContext.setUserCode(claims.get("userCode").toString());
                userContext.setUserAuthority((Boolean) claims.get("userAuthority"));
                return userContext;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
