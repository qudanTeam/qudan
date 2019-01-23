package com.qudan.qingcloud.msqudan.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import com.google.common.collect.Maps;
import com.qudan.qingcloud.msqudan.service.Impl.UserFinanceServiceImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.BasicHttpContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtil {
    private final static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private static PoolingHttpClientConnectionManager connectionManager;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 创建连接池
        connectionManager = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connectionManager.setMaxTotal(50);
        // 将每个路由基础的连接增加到getMaxTotal()
        connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());
        // 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
        connectionManager.setValidateAfterInactivity(10000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }

    public static Map<String,Object> doGetWithCookies(String url) {
        return doGetWithCookies(url, null);
    }

    public static String doGetCookiesWithCookies(String url) {
        String result = null;// 返回的数据
        HttpClient httpClient = null;
        if (url.startsWith("https")) {// 创建https连接
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } else {// 创建http连接
            httpClient = HttpClients.createDefault();
        }
        try {
            CookieStore cookieStore = new BasicCookieStore();
            HttpContext localContext = new BasicHttpContext();
            // Bind custom cookie store to the local context
            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("cache-control", "no-cache");
            httpGet.addHeader("", "");
            HttpResponse response = httpClient.execute(httpGet, localContext);
            // 获取返回的内容
            HttpEntity entity = response.getEntity();
            String cookieStr = "";
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                Cookie cookie = cookies.get(i);
                cookieStr += (cookie.getName()+"="+cookie.getValue()+"; ");
            }
            log.info("cookieStr: " + cookieStr);
            return cookieStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 发送 GET 请求
     *
     * @param url
     *            请求参数
     * @return
     */
    public static Map<String,Object> doGetWithCookies(String url, String cookieHeader) {
        Map<String,Object> objectMap = Maps.newHashMap();
        StringBuilder sb = new StringBuilder();

        String result = null;// 返回的数据
        HttpClient httpClient = null;
        if (url.startsWith("https")) {// 创建https连接
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } else {// 创建http连接
            httpClient = HttpClients.createDefault();
        }
        try {
            CookieStore cookieStore = new BasicCookieStore();
            HttpContext localContext = new BasicHttpContext();
            // Bind custom cookie store to the local context
            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Connection", "keep-alive");
            httpGet.addHeader("Cookie", cookieHeader);
            HttpResponse response = httpClient.execute(httpGet, localContext);
            // 获取返回的内容
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                objectMap.put("input", inputStream);
            }
            String cookieStr = "";
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                Cookie cookie = cookies.get(i);
                cookieStr += (cookie.getName()+"="+cookie.getValue()+";");
            }
            log.info("cookieStr: " + cookieStr);
            objectMap.put("cookieStr", cookieStr);
            return objectMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送 GET 请求
     *
     * @param url
     * @param params
     *            请求参数
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {

        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            if(sb.length() > 0)
                sb.append("&");
            else
                sb.append("?");
            sb.append(key).append("=").append(params.get(key));
        }
        url += sb.toString();
        String result = null;// 返回的数据
        HttpClient httpClient = null;
        if (url.startsWith("https")) {// 创建https连接
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } else {// 创建http连接
            httpClient = HttpClients.createDefault();
        }
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            // 获取返回的内容
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送 POST 请求
     *
     * @param url
     * @return
     */
    public static String doPOST(String url) {
        return doPOST(url, new HashMap<String, Object>());
    }


    /**
     * 发送 POST 请求，map
     *
     * @param url
     * @param params
     *            请求参数
     * @return
     */
    public static Map<String,Object> doPOSTWithCookieStr(String url, Map<String, Object> params, String cookieHeader) {
        Map<String,Object> objectMap = Maps.newHashMap();
        CloseableHttpClient httpClient = null;
        if (url.startsWith("https")) {// 创建https连接
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } else {// 创建http连接
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            CookieStore cookieStore = new BasicCookieStore();
            HttpContext localContext = new BasicHttpContext();
            // Bind custom cookie store to the local context
            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            httpPost.addHeader("Cookie", cookieHeader);
            response = httpClient.execute(httpPost);
            String cookieStr = "";
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                Cookie cookie = cookies.get(i);
                System.out.println("Local cookie: " + cookies.get(i));
                cookieStr += (cookie.toString()+";");
            }
            objectMap.put("cookieStr", cookieStr);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
            objectMap.put("httpStr", httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return objectMap;
    }

    /**
     * 发送 POST 请求，map
     *
     * @param url
     * @param params
     *            请求参数
     * @return
     */
    public static String doPOST(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = null;
        if (url.startsWith("https")) {// 创建https连接
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } else {// 创建http连接
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求，JSON形式
     *
     * @param apiUrl
     * @param json
     *            json对象
     * @return
     */
    public static String doPost(String apiUrl, Object json) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

}