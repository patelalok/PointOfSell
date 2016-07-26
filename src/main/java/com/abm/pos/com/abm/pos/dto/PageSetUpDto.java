package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/14/16.
 */
public class PageSetUpDto {

    private int id;
    private int tax;
    private String storeAddress;
    private String storeLogo;
    private String footerReceipt;

    public String getFooterReceipt() {
        return footerReceipt;
    }

    public void setFooterReceipt(String footerReceipt) {
        this.footerReceipt = footerReceipt;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
