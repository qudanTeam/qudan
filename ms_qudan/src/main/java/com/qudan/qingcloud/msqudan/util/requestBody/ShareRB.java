package com.qudan.qingcloud.msqudan.util.requestBody;

public class ShareRB {
    Integer shareType;
    Integer pid;
    String loadingUrl;

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getLoadingUrl() {
        return loadingUrl;
    }

    public void setLoadingUrl(String loadingUrl) {
        this.loadingUrl = loadingUrl;
    }
}
