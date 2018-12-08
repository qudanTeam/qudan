package com.qudan.qingcloud.msqudan.util;

import com.qudan.qingcloud.msqudan.util.responses.UserInfo;

public class LocalUserHelper {
    private final static ThreadLocal<UserInfo>  ACCOUNT_THREAD_LOCAL = new ThreadLocal<UserInfo>();

    public static void putUser(Object id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Integer.valueOf(id.toString()));
        ACCOUNT_THREAD_LOCAL.set(userInfo);
    }

    /**
     * 登陆账户id
     * @returns
     */
    public static Integer getUserId(){
        UserInfo userInfo = ACCOUNT_THREAD_LOCAL.get();
        if (userInfo == null){
            return null;
        }
        return userInfo.getId();
    }


    public static void destory(){
        ACCOUNT_THREAD_LOCAL.remove();
    }
}
