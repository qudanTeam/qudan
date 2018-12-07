package com.qudan.qingcloud.msqudan.emailAndSms.sms;

import java.util.Map;

/**
 * Created by word on 2017/4/20.
 */
public interface SmsService {
    boolean sendThing(String phone, String project, Map<String, Object> params);

    boolean sendMarketing(String phone, String project, Map<String, Object> params);
}
