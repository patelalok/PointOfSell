package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/25/16.
 */
public class TransactionPaymentDto {

    private int transactionPaymentId;
    private int transactionId;
    private String transactionDate;
    private int paymentId;
    private String paymentAmount;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionPaymentId() {
        return transactionPaymentId;
    }

    public void setTransactionPaymentId(int transactionPaymentId) {
        this.transactionPaymentId = transactionPaymentId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }







}
