package com.qudan.qingcloud.msqudan.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by word on 2016/7/4.
 */
public class PasswordUtils {

    private final static String salt = "QUDAN+13579";

    private final static String catSalt = "vc6d9%s1d3c79";

    public static String encodePassword(String password){

        return DigestUtils.md5Hex(password + salt);

    }

    public static String encodeCat(String cat){
        return DigestUtils.md5Hex(String.format(catSalt,cat));
    }

  /*  public static void main(String[] args) {
        System.out.println(PasswordUtils.encodePassword("123456"));
    }*/

}
