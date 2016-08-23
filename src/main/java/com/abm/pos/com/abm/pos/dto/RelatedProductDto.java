package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 8/16/16.
 */
public class RelatedProductDto {

    private String productNo;
    private String relatedProductNo;
    private int relatedProductId;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getRelatedProductNo() {
        return relatedProductNo;
    }

    public void setRelatedProductNo(String relatedProductNo) {
        this.relatedProductNo = relatedProductNo;
    }

    public int getRelatedProductId() {
        return relatedProductId;
    }

    public void setRelatedProductId(int relatedProductId) {
        this.relatedProductId = relatedProductId;
    }
}
