package com.qudan.qingcloud.msqudan.emailAndSms.sms;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.qudan.qingcloud.msqudan.util.QudanJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by word on 2017/4/20.
 */
public class SmsServiceSubmail implements SmsService {
    private static Logger logger = LoggerFactory.getLogger(SmsServiceSubmail.class);
    private String url;
    private String businessAppId;
    private String businessAppKey;

    private String marketingAppId;
    private String marketingAppAppkey;


     public boolean sendThing(String phone, String project, Map<String, Object> params) {
        return msgXsend(businessAppId, businessAppKey, phone, project, QudanJsonUtils.parseMapToJSON(params));
     }


    public boolean sendMarketing(String phone, String project, Map<String, Object> params) {
        return msgXsend(marketingAppId, marketingAppAppkey, phone, project, QudanJsonUtils.parseMapToJSON(params));
    }

    /**
     * 短信魔板
     * 发送短信给个人
     */

    public boolean msgXsend(String appId,String appKey, String phone, String project, String jsonParams){
        //新增 地址列表
        Map<String, Object> map = new TreeMap<String,Object>();
        map.put("appid", appId);
        map.put("to", phone);
        map.put("project", project);
        map.put("vars", jsonParams);
        map.put("signature", appKey);
        try {
            HttpResponse<String> response = Unirest.post(url)
                .header("content-type", "application/json")
                .header("cache-control", "no-cache").body(QudanJsonUtils.parseMapToJSON(map))
                .asString();
            String info = response.getBody();
            Map<String, Object> data = QudanJsonUtils.parseJSONToMap(info);
            String status = data.get("status").toString();
            if(status.equals("success")){
                logger.info("phone:" + phone + ", send_id:" + data.get("send_id").toString());
                return true;
            } else if(status.equals("error")){
                logger.error("phone:" + phone + ", code:" + data.get("code").toString());
                logger.error("phone:" + phone + ", msg:" + data.get("msg").toString());
                return false;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            logger.error("phone:" + phone, e);
        }
        return false;
    }

    public SmsServiceSubmail(String url, String businessAppId, String businessAppKey, String marketingAppId, String marketingAppAppkey) {
        this.url = url;
        this.businessAppId = businessAppId;
        this.businessAppKey = businessAppKey;
        this.marketingAppId = marketingAppId;
        this.marketingAppAppkey = marketingAppAppkey;
    }

    public SmsServiceSubmail() {
    }


}
