package com.qudan.qingcloud.msqudan.entity;

public class BankQuery {
    private Integer id;

    private String cardCat;

    private Integer bankId;

    private String name;

    private String jinjianDate;

    private String cardStatus;

    private Integer status;

    private String bankName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardCat() {
        return cardCat;
    }

    public void setCardCat(String cardCat) {
        this.cardCat = cardCat == null ? null : cardCat.trim();
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJinjianDate() {
        return jinjianDate;
    }

    public void setJinjianDate(String jinjianDate) {
        this.jinjianDate = jinjianDate == null ? null : jinjianDate.trim();
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }
}