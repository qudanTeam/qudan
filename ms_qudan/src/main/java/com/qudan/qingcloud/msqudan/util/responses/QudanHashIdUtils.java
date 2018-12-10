package com.qudan.qingcloud.msqudan.util.responses;

import io.swagger.models.auth.In;
import org.hashids.*;

public class QudanHashIdUtils {
    static Hashids hashids = new Hashids("msqudan", 8, "0123456789abcdefghijklmnopqrstuvwxyz");
    public static String encodeHashId(Integer id){
        return hashids.encode(id);
    }

    public static Integer decodeHashId(String code){
        return Integer.valueOf(hashids.decode(code)[0] + "");
    }
}
