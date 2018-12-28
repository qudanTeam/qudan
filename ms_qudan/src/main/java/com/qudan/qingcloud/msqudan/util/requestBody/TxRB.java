package com.qudan.qingcloud.msqudan.util.requestBody;

import java.math.BigDecimal;

public class TxRB {
    private String alipayNo;
    private BigDecimal txPrice;
    private String txName;

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo;
    }

    public BigDecimal getTxPrice() {
        return txPrice;
    }

    public void setTxPrice(BigDecimal txPrice) {
        this.txPrice = txPrice;
    }

    public String getTxName() {
        return txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }
}
