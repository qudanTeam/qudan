package com.qudan.qingcloud.msqudan.util;

import com.qudan.qingcloud.msqudan.util.responses.UserInfo;

public class LocalUserHelper {
    private final static ThreadLocal<UserInfo>  ACCOUNT_THREAD_LOCAL = new ThreadLocal<>();

    public static void putUser(Object id) {
        UserInfo account = new UserInfo();
        account.setId(Integer.valueOf(id.toString()));
        ACCOUNT_THREAD_LOCAL.set(account);
    }

    /**
     * 登陆账户id
     * @return
     */
    public static Integer getUserId(){
        UserInfo user = ACCOUNT_THREAD_LOCAL.get();
        if (user == null){
            return null;
        }
        return  user.getId();
    }


    public static void destory(){
        ACCOUNT_THREAD_LOCAL.remove();
    }
}
