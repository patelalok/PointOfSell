package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 3/1/17.
 */
public class ProductPriceByCustomerDto {

    private String productNo;
    private double retailPrice;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
}
