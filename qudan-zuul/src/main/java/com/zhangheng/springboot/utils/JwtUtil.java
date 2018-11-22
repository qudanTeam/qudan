package com.zhangheng.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/11/13.
 */
public class JwtUtil {

    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String APP_KEY = "qudan_key"; //进行数字签名的私钥，一定要保管好

    private static final String CLAIM_KEY_CREATED = "created";//创建时间


    /**
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
     * @param claims 当前用户信息
     * @param issuer 该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的
     * @param ttlMillis 什么时候过期，这里是一个Unix时间戳，是否使用是可选的
     * @param audience 接收该JWT的一方，是否使用是可选的
     * @return
     */
    public static String createJWT(Map<String,Object> claims,String issuer,String subject,long ttlMillis, String audience){


        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(APP_KEY);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        claims.put(CLAIM_KEY_CREATED,new Date());//创建时间
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(uuid)//随机jwt_id 这里用userid
                .setClaims(claims)//用户信息
                .setSubject(subject)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm,signingKey);
        //设置Token的过期时间
        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }
        return jwtBuilder.compact();

    }

    //私钥解密token信息
    public static Claims getClaims(String jwt) {
        Claims claims;
        try{
            claims = Jwts.parser()
                     .setSigningKey(DatatypeConverter.parseBase64Binary(APP_KEY))
                     .parseClaimsJws(jwt).getBody();
        }catch (Exception e){
            logger.error(e.getMessage());
            claims = null;
        }
        return claims;
    }

    public static String generateToken(Map<String, Object> claims) {
        //设置刷新时间
        long ttlMillis = 1000 * 60 * 60;//过期时间(单位毫秒)
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(APP_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)//设置超时间
                .signWith(signatureAlgorithm,signingKey)
                .compact();
    }

    /**
     * 刷新token
     */
    public static String refreshToken(String token) {

        String refreshedToken;
        try {
            final Claims claims = getClaims(token);
            if(claims != null){
                //不等于空的情况刷新token时间
                claims.put(CLAIM_KEY_CREATED,new Date());
                refreshedToken = generateToken(claims);
            }else{
                //token已经过期
                return null;
            }
        }catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }
}
