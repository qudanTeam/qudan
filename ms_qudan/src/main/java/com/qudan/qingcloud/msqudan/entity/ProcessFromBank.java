package com.qudan.qingcloud.msqudan.entity;

public class ProcessFromBank {
    private String name;
    private String cardCat;
    private String jinjianDate;
    private String cardStatus;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardCat() {
        return cardCat;
    }

    public void setCardCat(String cardCat) {
        this.cardCat = cardCat;
    }

    public String getJinjianDate() {
        return jinjianDate;
    }

    public void setJinjianDate(String jinjianDate) {
        this.jinjianDate = jinjianDate;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProcessFromBank{" +
                "name='" + name + '\'' +
                ", cardCat='" + cardCat + '\'' +
                ", jinjianDate='" + jinjianDate + '\'' +
                ", cardStatus='" + cardStatus + '\'' +
                ", status=" + status +
                '}';
    }
}
