package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/14/16.
 */
public class PageSetUpDto {

    private int id;
    private double tax;
    private String storeAddress;
    private String storeLogo;
    private String footerReceipt;
    private String storeEmail;
    private int receiptType;
    private int wholeSaleFlag;
    private int customReceiptFlag;

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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public int getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(int receiptType) {
        this.receiptType = receiptType;
    }

    public int getWholeSaleFlag() {
        return wholeSaleFlag;
    }

    public void setWholeSaleFlag(int wholeSaleFlag) {
        this.wholeSaleFlag = wholeSaleFlag;
    }

    public int getCustomReceiptFlag() {
        return customReceiptFlag;
    }

    public void setCustomReceiptFlag(int customReceiptFlag) {
        this.customReceiptFlag = customReceiptFlag;
    }
}
