package com.qudan.qingcloud.msqudan.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qudan.qingcloud.msqudan.config.CommonConfig;
import java.io.IOException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by word on 2018/12/23.
 */
public class UploadToQiniu {
    //要上传的空间
    String bucketname = "Bucket_Name";
    //上传到七牛后保存的完整文件名,前缀+文件名
    String key = "images/users/2015-12-21/o_1a71qb1dn1hhng7g1fd8100c1hu2r.JPG";

    byte[] bytes = null;
    //密钥
    Auth auth;
    //上传对象
    UploadManager uploadManager;

    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String upload() {
        try {
            //调用put方法上传
            Response res = uploadManager.put(bytes, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return key;
    }

    public String uploadReturnPath(byte[] bytes) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(bytes, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
            return key;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }

    /**
     * @param commonConfig
     * @param type         moore或者mooreiot
     * @param fileType     img或者file
     * @param suffix       "/.../..."; 路径前缀
     * @param bytes        字节文件
     * @param orgFileName  源文件名称
     */

    public UploadToQiniu(CommonConfig commonConfig, String type, String fileType, String suffix, String orgFileName, byte[] bytes) {
        setBucketname(commonConfig.getQiniuUploadImageBucket());
        //密钥配置
        auth = Auth.create(commonConfig.getQiniuAccesskey(), commonConfig.getQiniuSecretkey());
        //创建上传对象
        uploadManager = new UploadManager();
        if (orgFileName.indexOf(".") > -1) {
            setKey(suffix + DateUtil.getCurrentDate("yyyy-MM-dd") + "/" + DateUtil.getCurrentDate("HH_mm_ss") + RandomUtils.generateLowerString(10) + orgFileName.substring(orgFileName.indexOf("."), orgFileName.length()));
        } else {
            setKey(suffix + DateUtil.getCurrentDate("yyyy-MM-dd") + "/" + DateUtil.getCurrentDate("HH_mm_ss") + RandomUtils.generateLowerString(10) + "." + "png");
        }
        setBytes(bytes);
    }

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}