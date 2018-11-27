package com.qudan.qingcloud.msqudan.entity.self.response;

import com.qudan.qingcloud.msqudan.entity.Product;
import com.qudan.qingcloud.msqudan.entity.ShareManager;

import java.util.List;

public class ProductVo {
    private Product product;
    private List<ShareManager> shares;
    private String bank;
    private String bankQueryLink;
    private List<String> loanTag;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ShareManager> getShares() {
        return shares;
    }

    public void setShares(List<ShareManager> shares) {
        this.shares = shares;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankQueryLink() {
        return bankQueryLink;
    }

    public void setBankQueryLink(String bankQueryLink) {
        this.bankQueryLink = bankQueryLink;
    }

    public List<String> getLoanTag() {
        return loanTag;
    }

    public void setLoanTag(List<String> loanTag) {
        this.loanTag = loanTag;
    }
}
