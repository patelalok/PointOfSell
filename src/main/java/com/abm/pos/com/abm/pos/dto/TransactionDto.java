package com.abm.pos.com.abm.pos.dto;

import java.util.List;

/**
 * Created by asp5045 on 5/20/16.
 */
public class TransactionDto {

    private int transactionCompId;
    private String transactionDate;
    private double totalAmount;
    private double tax;
    private double discount;
    private double subTotal;
    private String customerPhoneNo;
    private int userId;
    private int cashId;
    private int creditId;
    private String status;
    private double  paidAmountCash;
    private double changeAmount;
    private double paidAmountCredit;
    private String username;
    private List<TransactionLineItemDto> transactionLineItemDtoList;
    private int totalQuantity;
    private String transCreditId;
    private int last4Digits;

    public int getLast4Digits() {
        return last4Digits;
    }

    public void setLast4Digits(int last4Digits) {
        this.last4Digits = last4Digits;
    }

    public String getTransCreditId() {
        return transCreditId;
    }

    public void setTransCreditId(String transCreditId) {
        this.transCreditId = transCreditId;
    }



    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }



    public List<TransactionLineItemDto> getTransactionLineItemDtoList() {
        return transactionLineItemDtoList;
    }

    public void setTransactionLineItemDtoList(List<TransactionLineItemDto> transactionLineItemDtoList) {
        this.transactionLineItemDtoList = transactionLineItemDtoList;
    }



    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public double getPaidAmountCash() {
        return paidAmountCash;
    }

    public void setPaidAmountCash(double paidAmountCash) {
        this.paidAmountCash = paidAmountCash;
    }

    public double getPaidAmountCredit() {
        return paidAmountCredit;
    }

    public void setPaidAmountCredit(double paidAmountCredit) {
        this.paidAmountCredit = paidAmountCredit;
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
