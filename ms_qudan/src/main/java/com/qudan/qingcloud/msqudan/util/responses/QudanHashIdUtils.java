package com.qudan.qingcloud.msqudan.util.responses;

import io.swagger.models.auth.In;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hashids.*;

public class QudanHashIdUtils {
    protected final static Log logger = LogFactory.getLog(QudanHashIdUtils.class);

    static Hashids hashids = new Hashids("msqudan", 8, "0123456789abcdefghijklmnopqrstuvwxyz");
    public static String encodeHashId(Integer id){
        return hashids.encode(id);
    }

    public static Integer decodeHashId(String code){
        Integer id = null;
        try {
            id = Integer.valueOf(hashids.decode(code)[0] + "");
        }catch (Throwable ex){
            logger.error("解密QudanHash错误 code:"+ code + ", ", ex);
        }
        return id;
    }
}
