package com.abm.pos.com.abm.pos.dto;

/**
 * Created by apatel2 on 6/25/17.
 */
public class OrderDto {

    private int orderId;
    private String productNo;
    private double costPrice;
    private double retailPrice;
    private int quantity;
    private String username;
    private String lastUpdatedOrderDate;
    private int vendorId;
    private String vendorName;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastUpdatedOrderDate() {
        return lastUpdatedOrderDate;
    }

    public void setLastUpdatedOrderDate(String lastUpdatedOrderDate) {
        this.lastUpdatedOrderDate = lastUpdatedOrderDate;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
