package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */
public class TransactionLineItemDto {

    private int transactionLineItemId;
    private int transactionCompId;
    private int productId;
    private String productNumber;
    private int quantity;
    private double retail;
    private double cost;
    private double discount;
    private String date;
    private String ProductDescription;
    private String  productCount;
    private double retailWithDis;
    private double totalProductPrice;

    public double getRetailWithDis() {
        return retailWithDis;
    }

    public void setRetailWithDis(double retailWithDis) {
        this.retailWithDis = retailWithDis;
    }

    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }



    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }



    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }




    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTransactionCompId() {
        return transactionCompId;
    }

    public void setTransactionCompId(int transactionCompId) {
        this.transactionCompId = transactionCompId;
    }




    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRetail() {
        return retail;
    }

    public void setRetail(double retail) {
        this.retail = retail;
    }


    public int getTransactionLineItemId() {
        return transactionLineItemId;
    }

    public void setTransactionLineItemId(int transactionLineItemId) {
        this.transactionLineItemId = transactionLineItemId;
    }




}
