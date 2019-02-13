package com.qudan.qingcloud.msqudan.util.responses;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hashids.Hashids;

public class QudanHashId12Utils {
    protected final static Log logger = LogFactory.getLog(QudanHashId12Utils.class);

    static Hashids hashids = new Hashids("msqudan", 12, "0123456789abcdefghijklmnopqrstuvwxyz");
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

    public static void main(String[] args) {
        System.out.println(decodeHashId(""));
    }
}
