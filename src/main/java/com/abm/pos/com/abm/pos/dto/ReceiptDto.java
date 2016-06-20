package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 6/19/16.
 */
public class ReceiptDto {

    private int transactionCompId;
    private String transactionDate;
    private double totalAmount;
    private double tax;
    private double transactionDiscount;
    private String customerPhoneNo;
    private int userId;
    private int paymentId;
    private String status;
    private double  paidAmount;
    private double changeAmount;
    private String username;
    private int paymentIdMulty;
    private double paidAmountMulty;

    private int transactionLineItemId;
    private int productId;
    private int quantity;
    private double retail;
    private double cost;
    private double productDiscount;

    private String paymentType;

    private String storeDetails;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getPaidAmountMulty() {
        return paidAmountMulty;
    }

    public void setPaidAmountMulty(double paidAmountMulty) {
        this.paidAmountMulty = paidAmountMulty;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentIdMulty() {
        return paymentIdMulty;
    }

    public void setPaymentIdMulty(int paymentIdMulty) {
        this.paymentIdMulty = paymentIdMulty;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(String storeDetails) {
        this.storeDetails = storeDetails;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTransactionCompId() {
        return transactionCompId;
    }

    public void setTransactionCompId(int transactionCompId) {
        this.transactionCompId = transactionCompId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionDiscount() {
        return transactionDiscount;
    }

    public void setTransactionDiscount(double transactionDiscount) {
        this.transactionDiscount = transactionDiscount;
    }

    public int getTransactionLineItemId() {
        return transactionLineItemId;
    }

    public void setTransactionLineItemId(int transactionLineItemId) {
        this.transactionLineItemId = transactionLineItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}

