package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/9/16.
 */
public class AddProductDto {

    private String productId;



    private String vendorId;
    private String description;
    private String categoryId;
    private String upcCode;
    private String altNo;
    private String costPrice;
    private String retailPrice;
    private String quantity;
    private String minProductQuantity;
    private String returnRule;
    private String image;

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public void setUpcCode(String upcCode) {
        this.upcCode = upcCode;
    }

    public String getAltNo() {
        return altNo;
    }

    public void setAltNo(String altNo) {
        this.altNo = altNo;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMinProductQuantity() {
        return minProductQuantity;
    }

    public void setMinProductQuantity(String minProductQuantity) {
        this.minProductQuantity = minProductQuantity;
    }

    public String getReturnRule() {
        return returnRule;
    }

    public void setReturnRule(String returnRule) {
        this.returnRule = returnRule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




}
