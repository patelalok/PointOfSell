package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/11/16.
 */
public class DashboardDto {

    private String nameOfMonth;
    private double total;
    private double cash;
    private double credit;
    private double check;
    private double debit;
    private double profit;
    private double tax;
    private double discount;
    private double noOfTrans;
    private double avgBasketSize;
    private double balance;

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCheck() {
        return check;
    }

    public void setCheck(double check) {
        this.check = check;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(double noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public double getAvgBasketSize() {
        return avgBasketSize;
    }

    public void setAvgBasketSize(double avgBasketSize) {
        this.avgBasketSize = avgBasketSize;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
