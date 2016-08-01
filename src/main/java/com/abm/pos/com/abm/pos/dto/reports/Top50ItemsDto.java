package com.abm.pos.com.abm.pos.dto.reports;

/**
 * Created by asp5045 on 7/4/16.
 */
public class Top50ItemsDto {

    private String productNo;
    private String productName;
    private int quantity;
    private double productSumRetailPrice;
    private double productSumCostPrice;
    private double retailPrice;
    private double costPrice;
    private double productProfitAmount;

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public double getProductProfitAmount() {
        return productProfitAmount;
    }

    public void setProductProfitAmount(double productProfitAmount) {
        this.productProfitAmount = productProfitAmount;
    }

    public double getProductSumCostPrice() {
        return productSumCostPrice;
    }

    public void setProductSumCostPrice(double productSumCostPrice) {
        this.productSumCostPrice = productSumCostPrice;
    }

    public double getProductSumRetailPrice() {
        return productSumRetailPrice;
    }

    public void setProductSumRetailPrice(double productSumRetailPrice) {
        this.productSumRetailPrice = productSumRetailPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
    // FIND THE WAY TO GET THESE #3 AS WELL
   /* private String vendorName;
    private String categoryName;
    private String brandName;
   public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

  public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }*/


}
