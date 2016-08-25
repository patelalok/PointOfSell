package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 8/24/16.
 */
public class PhoneDto {

    private int phoneId;
    private String productNo;
    private String imeiNo;
    private double costPrice;
    private double retailPrice;
    private double markup;
    private String lastUpdatedTimeStamp;

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }

    public String getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    public void setLastUpdatedTimeStamp(String lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }
}
