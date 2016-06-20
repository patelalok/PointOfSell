package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */
public class TransactionDto {




    private int transactionCompId;
    private String transactionDate;
    private double totalAmount;
    private double tax;
    private double discount;
    private String customerPhoneNo;
    private int userId;
    private int paymentId;
    private String status;
    private double  paidAmount;
    private double changeAmount;
    private String username;
    private int paymentIdMulty;
    private double paidAmountMulty;

    public int getPaymentIdMulty() {
        return paymentIdMulty;
    }

    public void setPaymentIdMulty(int paymentIdMulty) {
        this.paymentIdMulty = paymentIdMulty;
    }

    public double getPaidAmountMulty() {
        return paidAmountMulty;
    }

    public void setPaidAmountMulty(double paidAmountMulty) {
        this.paidAmountMulty = paidAmountMulty;
    }



    public int getTransactionCompId() {
        return transactionCompId;
    }

    public void setTransactionCompId(int transactionCompId) {
        this.transactionCompId = transactionCompId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }



    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }



    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }






}
