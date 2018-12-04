package com.qudan.qingcloud.msqudan.wx;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.locks.Lock;

/**
 * Created by word on 2016/12/15.
 */
public class WxMpServiceIm extends WxMpServiceImpl {

    private String ACCESS_TOKEN_URL;
    private String JS_TICKEY_URL;

    public WxMpServiceIm(String accessTokenUrl, String jsTickeyUrl) {
        ACCESS_TOKEN_URL = accessTokenUrl;
        JS_TICKEY_URL = jsTickeyUrl;
    }

    public WxMpServiceIm() {
    }

    @Override
    public String getAccessToken() throws WxErrorException {
        return this.getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        Lock lock = this.getWxMpConfigStorage().getAccessTokenLock();

        try {
            lock.lock();
            if(forceRefresh) {
                this.getWxMpConfigStorage().expireAccessToken();
            }

            if(this.getWxMpConfigStorage().isAccessTokenExpired()) {
                String url = ACCESS_TOKEN_URL;
                try {
                    HttpGet e = new HttpGet(url);
                    if(this.getRequestHttpProxy() != null) {
                        RequestConfig response = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
                        e.setConfig(response);
                    }

                    try {
                        CloseableHttpResponse response1 = this.getRequestHttpClient().execute(e);
                        Throwable var6 = null;

                        try {
                            String x2 = (new BasicResponseHandler()).handleResponse(response1);
                            WxError error = WxError.fromJson(x2);
                            if(error.getErrorCode() != 0) {
                                throw new WxErrorException(error);
                            }

                            WxAccessToken accessToken = WxAccessToken.fromJson(x2);
                            this.getWxMpConfigStorage().updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
                        } catch (Throwable var36) {
                            var6 = var36;
                            throw var36;
                        } finally {
                            if(response1 != null) {
                                if(var6 != null) {
                                    try {
                                        response1.close();
                                    } catch (Throwable var35) {
                                        var6.addSuppressed(var35);
                                    }
                                } else {
                                    response1.close();
                                }
                            }

                        }
                    } finally {
                        e.releaseConnection();
                    }
                } catch (IOException var39) {
                    throw new RuntimeException(var39);
                }
            }
        } finally {
            lock.unlock();
        }

        return this.getWxMpConfigStorage().getAccessToken();
    }

    @Override
    public String getJsapiTicket() throws WxErrorException {
        return this.getJsapiTicket(false);
    }

    @Override
    public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
        Lock lock = this.getWxMpConfigStorage().getJsapiTicketLock();

        try {
            lock.lock();
            if(forceRefresh) {
                this.getWxMpConfigStorage().expireJsapiTicket();
            }
            if(this.getWxMpConfigStorage().isJsapiTicketExpired()) {
                String url = JS_TICKEY_URL;
                HttpGet e = new HttpGet(url);
                if (this.getRequestHttpProxy() != null) {
                    RequestConfig response = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
                    e.setConfig(response);
                }
                try {
                    CloseableHttpResponse response1 = this.getRequestHttpClient().execute(e);
                    String resultContent = (new BasicResponseHandler()).handleResponse(response1);
                    WxError error = WxError.fromJson(resultContent);
                    if (error.getErrorCode() != 0) {
                        throw new WxErrorException(error);
                    }
                    JsonElement tmpJsonElement = Streams.parse(new JsonReader(new StringReader(resultContent)));
                    JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
                    String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
                    int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
                    this.getWxMpConfigStorage().updateJsapiTicket(jsapiTicket, expiresInSeconds);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

        return this.getWxMpConfigStorage().getJsapiTicket();
    }

//    @Override
//    public String getAccessToken() throws WxErrorException {
//        return getAccessToken(false);
//    }
//
//    @Override
//    public String getAccessToken(boolean forceRefresh) throws WxErrorException {
//        if(forceRefresh) {
//            this.wxMpConfigStorage.expireAccessToken();
//        }
//
//        if(this.wxMpConfigStorage.isAccessTokenExpired()) {
//            Object var2 = this.globalAccessTokenRefreshLock;
//            synchronized(this.globalAccessTokenRefreshLock) {
//                if(this.wxMpConfigStorage.isAccessTokenExpired()) {
//                    String url = ACCESS_TOKEN_URL;
//
//                    try {
//                        HttpGet e = new HttpGet(url);
//                        if(this.httpProxy != null) {
//                            RequestConfig response = RequestConfig.custom().setProxy(this.httpProxy).build();
//                            e.setConfig(response);
//                        }
//
//                        CloseableHttpResponse response1 = this.getHttpclient().execute(e);
//                        String resultContent = (new BasicResponseHandler()).handleResponse(response1);
//                        WxError error = WxError.fromJson(resultContent);
//                        if(error.getErrorCode() != 0) {
//                            throw new WxErrorException(error);
//                        }
//
//                        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
//                        this.wxMpConfigStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
//                    } catch (ClientProtocolException var10) {
//                        throw new RuntimeException(var10);
//                    } catch (IOException var11) {
//                        throw new RuntimeException(var11);
//                    }
//                }
//            }
//        }
//
//        return this.wxMpConfigStorage.getAccessToken();
//    }
//
//    @Override
//    public String getJsapiTicket() throws WxErrorException {
//        return getJsapiTicket(false);
//    }
//    @Override
//    public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
//        if(forceRefresh) {
//            this.wxMpConfigStorage.expireJsapiTicket();
//        }
//
//        if(this.wxMpConfigStorage.isJsapiTicketExpired()) {
//            Object var2 = this.globalJsapiTicketRefreshLock;
//            synchronized(this.globalJsapiTicketRefreshLock) {
//                if(this.wxMpConfigStorage.isJsapiTicketExpired()) {
//                    String url = JS_TICKEY_URL;
//                    HttpGet e = new HttpGet(url);
//                    if(this.httpProxy != null) {
//                        RequestConfig response = RequestConfig.custom().setProxy(this.httpProxy).build();
//                        e.setConfig(response);
//                    }
//
//                    try {
//                        CloseableHttpResponse response1 = this.getHttpclient().execute(e);
//                        String resultContent = (new BasicResponseHandler()).handleResponse(response1);
//                        WxError error = WxError.fromJson(resultContent);
//                        if(error.getErrorCode() != 0) {
//                            throw new WxErrorException(error);
//                        }
//                        JsonElement tmpJsonElement = Streams.parse(new JsonReader(new StringReader(resultContent)));
//                        JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
//                        String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
//                        int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
//                        this.wxMpConfigStorage.updateJsapiTicket(jsapiTicket, expiresInSeconds);
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//
//
//
//
//                }
//            }
//        }
//
//        return this.wxMpConfigStorage.getJsapiTicket();
//    }


}
