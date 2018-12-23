package com.qudan.qingcloud.msqudan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private String productName;

    private String logo;

    private Integer productType;

    private Integer isHot;

    private Integer isShow;

    private Integer customer;

    private Integer isInShop;

    private Integer isShelf;

    private Date createTime;

    private Date modifyTime;

    private BigDecimal commission;

    private Integer sortVal;

    private String bgCategory;

    private Integer amountLine;

    private String progressQueryImg;

    private BigDecimal allowRate;

    private Integer applyNum;

    private String applyCondition;

    private String applyTpImg;

    private BigDecimal dayRate;

    private BigDecimal monthRate;

    private Integer aBegin;

    private Integer aLimit;

    private Integer bBegin;

    private Integer bLimit;

    private Integer cStart;

    private Integer cLimit;

    private BigDecimal aLevelReward;

    private BigDecimal bLevelReward;

    private BigDecimal cLevelReward;

    private BigDecimal baseSalary;

    private String monthSalary;

    private String salary;

    private String salaryDesc;

    private String monthSalaryDesc;

    private String secondSummary;

    private String thirdSummary;

    private String detailHeaderImg;

    private String cardLongImg;

    private String productShowImg;

    private String burundian;

    private Integer settlementType;

    private String expireUnit;

    private String howSettle;

    private Integer expireBegin;

    private Integer expireEnd;

    private String commissionStandard;

    private String shareTitle;

    private String cardProgressImg;

    private String baseRight;

    private String preferential;

    private String specialTag;

    private String specialTxt;

    private String unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getIsInShop() {
        return isInShop;
    }

    public void setIsInShop(Integer isInShop) {
        this.isInShop = isInShop;
    }

    public Integer getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Integer isShelf) {
        this.isShelf = isShelf;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public String getBgCategory() {
        return bgCategory;
    }

    public void setBgCategory(String bgCategory) {
        this.bgCategory = bgCategory == null ? null : bgCategory.trim();
    }

    public Integer getAmountLine() {
        return amountLine;
    }

    public void setAmountLine(Integer amountLine) {
        this.amountLine = amountLine;
    }

    public String getProgressQueryImg() {
        return progressQueryImg;
    }

    public void setProgressQueryImg(String progressQueryImg) {
        this.progressQueryImg = progressQueryImg == null ? null : progressQueryImg.trim();
    }

    public BigDecimal getAllowRate() {
        return allowRate;
    }

    public void setAllowRate(BigDecimal allowRate) {
        this.allowRate = allowRate;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition == null ? null : applyCondition.trim();
    }

    public String getApplyTpImg() {
        return applyTpImg;
    }

    public void setApplyTpImg(String applyTpImg) {
        this.applyTpImg = applyTpImg == null ? null : applyTpImg.trim();
    }

    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    public BigDecimal getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(BigDecimal monthRate) {
        this.monthRate = monthRate;
    }

    public Integer getaBegin() {
        return aBegin;
    }

    public void setaBegin(Integer aBegin) {
        this.aBegin = aBegin;
    }

    public Integer getaLimit() {
        return aLimit;
    }

    public void setaLimit(Integer aLimit) {
        this.aLimit = aLimit;
    }

    public Integer getbBegin() {
        return bBegin;
    }

    public void setbBegin(Integer bBegin) {
        this.bBegin = bBegin;
    }

    public Integer getbLimit() {
        return bLimit;
    }

    public void setbLimit(Integer bLimit) {
        this.bLimit = bLimit;
    }

    public Integer getcStart() {
        return cStart;
    }

    public void setcStart(Integer cStart) {
        this.cStart = cStart;
    }

    public Integer getcLimit() {
        return cLimit;
    }

    public void setcLimit(Integer cLimit) {
        this.cLimit = cLimit;
    }

    public BigDecimal getaLevelReward() {
        return aLevelReward;
    }

    public void setaLevelReward(BigDecimal aLevelReward) {
        this.aLevelReward = aLevelReward;
    }

    public BigDecimal getbLevelReward() {
        return bLevelReward;
    }

    public void setbLevelReward(BigDecimal bLevelReward) {
        this.bLevelReward = bLevelReward;
    }

    public BigDecimal getcLevelReward() {
        return cLevelReward;
    }

    public void setcLevelReward(BigDecimal cLevelReward) {
        this.cLevelReward = cLevelReward;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(String monthSalary) {
        this.monthSalary = monthSalary == null ? null : monthSalary.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getSalaryDesc() {
        return salaryDesc;
    }

    public void setSalaryDesc(String salaryDesc) {
        this.salaryDesc = salaryDesc == null ? null : salaryDesc.trim();
    }

    public String getMonthSalaryDesc() {
        return monthSalaryDesc;
    }

    public void setMonthSalaryDesc(String monthSalaryDesc) {
        this.monthSalaryDesc = monthSalaryDesc == null ? null : monthSalaryDesc.trim();
    }

    public String getSecondSummary() {
        return secondSummary;
    }

    public void setSecondSummary(String secondSummary) {
        this.secondSummary = secondSummary == null ? null : secondSummary.trim();
    }

    public String getThirdSummary() {
        return thirdSummary;
    }

    public void setThirdSummary(String thirdSummary) {
        this.thirdSummary = thirdSummary == null ? null : thirdSummary.trim();
    }

    public String getDetailHeaderImg() {
        return detailHeaderImg;
    }

    public void setDetailHeaderImg(String detailHeaderImg) {
        this.detailHeaderImg = detailHeaderImg == null ? null : detailHeaderImg.trim();
    }

    public String getCardLongImg() {
        return cardLongImg;
    }

    public void setCardLongImg(String cardLongImg) {
        this.cardLongImg = cardLongImg == null ? null : cardLongImg.trim();
    }

    public String getProductShowImg() {
        return productShowImg;
    }

    public void setProductShowImg(String productShowImg) {
        this.productShowImg = productShowImg == null ? null : productShowImg.trim();
    }

    public String getBurundian() {
        return burundian;
    }

    public void setBurundian(String burundian) {
        this.burundian = burundian == null ? null : burundian.trim();
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public String getExpireUnit() {
        return expireUnit;
    }

    public void setExpireUnit(String expireUnit) {
        this.expireUnit = expireUnit == null ? null : expireUnit.trim();
    }

    public String getHowSettle() {
        return howSettle;
    }

    public void setHowSettle(String howSettle) {
        this.howSettle = howSettle == null ? null : howSettle.trim();
    }

    public Integer getExpireBegin() {
        return expireBegin;
    }

    public void setExpireBegin(Integer expireBegin) {
        this.expireBegin = expireBegin;
    }

    public Integer getExpireEnd() {
        return expireEnd;
    }

    public void setExpireEnd(Integer expireEnd) {
        this.expireEnd = expireEnd;
    }

    public String getCommissionStandard() {
        return commissionStandard;
    }

    public void setCommissionStandard(String commissionStandard) {
        this.commissionStandard = commissionStandard == null ? null : commissionStandard.trim();
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getCardProgressImg() {
        return cardProgressImg;
    }

    public void setCardProgressImg(String cardProgressImg) {
        this.cardProgressImg = cardProgressImg == null ? null : cardProgressImg.trim();
    }

    public String getBaseRight() {
        return baseRight;
    }

    public void setBaseRight(String baseRight) {
        this.baseRight = baseRight == null ? null : baseRight.trim();
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential == null ? null : preferential.trim();
    }

    public String getSpecialTag() {
        return specialTag;
    }

    public void setSpecialTag(String specialTag) {
        this.specialTag = specialTag == null ? null : specialTag.trim();
    }

    public String getSpecialTxt() {
        return specialTxt;
    }

    public void setSpecialTxt(String specialTxt) {
        this.specialTxt = specialTxt == null ? null : specialTxt.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}