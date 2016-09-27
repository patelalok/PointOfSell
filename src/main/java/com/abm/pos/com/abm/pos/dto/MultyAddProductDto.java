package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/27/16.
 */
public class MultyAddProductDto {
    private int productId;
    private String productNo;
    private String categoryId;
    private String vendorId;
    private String brandId;
    private String altNo;
    private String description;
    private String costPrice;
    private String markup;
    private String retailPrice;
    private String quantity;
    private int minProductQuantity;
    private String returnRule;
    private String image;
    private String createdDate;
    private String categoryName;
    private String brandName;
    private String vendorName;
    private String imeiNo;
    private String tax;
    private int stock;


    //Delete it when you done with nur bhai.
    private double testProductNo;

    private int quantityForSell;

    public String getAltNo() {
        return altNo;
    }

    public void setAltNo(String altNo) {
        this.altNo = altNo;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public int getMinProductQuantity() {
        return minProductQuantity;
    }

    public void setMinProductQuantity(int minProductQuantity) {
        this.minProductQuantity = minProductQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getQuantityForSell() {
        return quantityForSell;
    }

    public void setQuantityForSell(int quantityForSell) {
        this.quantityForSell = quantityForSell;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getReturnRule() {
        return returnRule;
    }

    public void setReturnRule(String returnRule) {
        this.returnRule = returnRule;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getTestProductNo() {
        return testProductNo;
    }

    public void setTestProductNo(double testProductNo) {
        this.testProductNo = testProductNo;
    }
}
