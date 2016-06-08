package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/17/16.
 */
public class BrandDto {

    private int brandId;
    private String brandName;
    private String brandDescription;
    private int noOfProducts;

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }





}
