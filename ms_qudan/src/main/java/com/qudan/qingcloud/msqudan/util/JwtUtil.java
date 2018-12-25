package com.qudan.qingcloud.msqudan.util;

import io.jsonwebtoken.*;
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

    public static final String APP_KEY = "qudan_key"; //进行数字签名的私钥，一定要保管好

    private static final String CLAIM_KEY_CREATED = "created";//创建时间

    //私钥解密token信息
    public static Claims getClaims(String jwt) throws SignatureException,ExpiredJwtException,StringIndexOutOfBoundsException {
        Claims claims;
        claims = Jwts.parser()
                 .setSigningKey(DatatypeConverter.parseBase64Binary(APP_KEY))
                 .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
