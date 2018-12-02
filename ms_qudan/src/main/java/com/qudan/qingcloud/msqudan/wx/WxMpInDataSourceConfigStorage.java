package com.qudan.qingcloud.msqudan.wx;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 汤云飞 on 2016/8/13.
 */
public class WxMpInDataSourceConfigStorage implements WxMpConfigStorage {
    protected volatile String appId;
    protected volatile String secret;
    protected volatile String partnerId;
    protected volatile String partnerKey;
    protected volatile String notifyURL;
    protected volatile String tradeType;
    protected volatile String token;
    protected volatile String accessToken;
    protected volatile String aesKey;
    protected volatile long expiresTime;
    protected volatile String oauth2redirectUri;
    protected volatile String httpProxyHost;
    protected volatile int httpProxyPort;
    protected volatile String httpProxyUsername;
    protected volatile String httpProxyPassword;
    protected volatile String jsapiTicket;
    protected volatile long jsapiTicketExpiresTime;
    protected volatile String cardApiTicket;
    protected volatile long cardApiTicketExpiresTime;
    protected Lock accessTokenLock = new ReentrantLock();
    protected Lock jsapiTicketLock = new ReentrantLock();
    protected Lock cardApiTicketLock = new ReentrantLock();
    protected volatile File tmpDirFile;
    protected volatile SSLContext sslContext;
    protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

    public WxMpInDataSourceConfigStorage() {
    }

    public WxMpInDataSourceConfigStorage(String weixinMoorerenAppid, String weixinMoorerenAppsecret, String weixinMoorerenToken, String weixinMoorerenEncodingaeskey) {
        this.appId = weixinMoorerenAppid;
        this.secret = weixinMoorerenAppsecret;
        this.token = weixinMoorerenToken;
        this.aesKey = weixinMoorerenEncodingaeskey;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public Lock getAccessTokenLock() {
        return this.accessTokenLock;
    }

    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    public synchronized void updateAccessToken(WxAccessToken accessToken) {
        this.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (long)(expiresInSeconds - 200) * 1000L;
    }

    public void expireAccessToken() {
        this.expiresTime = 0L;
    }

    public String getJsapiTicket() {
        return this.jsapiTicket;
    }

    public Lock getJsapiTicketLock() {
        return this.jsapiTicketLock;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public long getJsapiTicketExpiresTime() {
        return this.jsapiTicketExpiresTime;
    }

    public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
        this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
    }

    public boolean isJsapiTicketExpired() {
        return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
    }

    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        this.jsapiTicket = jsapiTicket;
        this.jsapiTicketExpiresTime = System.currentTimeMillis() + (long)(expiresInSeconds - 200) * 1000L;
    }

    public void expireJsapiTicket() {
        this.jsapiTicketExpiresTime = 0L;
    }

    public String getCardApiTicket() {
        return this.cardApiTicket;
    }

    public Lock getCardApiTicketLock() {
        return this.cardApiTicketLock;
    }

    public boolean isCardApiTicketExpired() {
        return System.currentTimeMillis() > this.cardApiTicketExpiresTime;
    }

    public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
        this.cardApiTicket = cardApiTicket;
        this.cardApiTicketExpiresTime = System.currentTimeMillis() + (long)(expiresInSeconds - 200) * 1000L;
    }

    public void expireCardApiTicket() {
        this.cardApiTicketExpiresTime = 0L;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getSecret() {
        return this.secret;
    }

    public String getToken() {
        return this.token;
    }

    public long getExpiresTime() {
        return this.expiresTime;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return this.aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getOauth2redirectUri() {
        return this.oauth2redirectUri;
    }

    public void setOauth2redirectUri(String oauth2redirectUri) {
        this.oauth2redirectUri = oauth2redirectUri;
    }

    public String getHttpProxyHost() {
        return this.httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    public int getHttpProxyPort() {
        return this.httpProxyPort;
    }

    public void setHttpProxyPort(int httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    public String getHttpProxyUsername() {
        return this.httpProxyUsername;
    }

    public void setHttpProxyUsername(String httpProxyUsername) {
        this.httpProxyUsername = httpProxyUsername;
    }

    public String getHttpProxyPassword() {
        return this.httpProxyPassword;
    }

    public void setHttpProxyPassword(String httpProxyPassword) {
        this.httpProxyPassword = httpProxyPassword;
    }

    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerKey() {
        return this.partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getNotifyURL() {
        return this.notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public String getTradeType() {
        return this.tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public File getTmpDirFile() {
        return this.tmpDirFile;
    }

    public void setTmpDirFile(File tmpDirFile) {
        this.tmpDirFile = tmpDirFile;
    }

    public SSLContext getSSLContext() {
        return this.sslContext;
    }

    public void setSSLContext(SSLContext context) {
        this.sslContext = context;
    }

    public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
        return this.apacheHttpClientBuilder;
    }

    public boolean autoRefreshToken() {
        return true;
    }

    public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
        this.apacheHttpClientBuilder = apacheHttpClientBuilder;
    }

}
