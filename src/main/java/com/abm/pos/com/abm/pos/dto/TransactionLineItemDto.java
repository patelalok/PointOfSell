package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */
public class TransactionLineItemDto {

    private int transactionLineItemId;
    private int transactionId;
    private int productId;
    private int quantity;
    private double retail;
    private double cost;
    private double tax;
    private double discount;


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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionLineItemId() {
        return transactionLineItemId;
    }

    public void setTransactionLineItemId(int transactionLineItemId) {
        this.transactionLineItemId = transactionLineItemId;
    }




}
