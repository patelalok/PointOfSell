package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 6/27/16.
 */
public class DailyTransactionDto {

    private String date;
    private double Cash;
    private double credit;
    private double check;
    private double debit;
    private double tax;
    private double discount;
    private double total;
    private double grossSale;
    private double markup;
    private int noOfTransactions;
    private double avgTotal;
    private double profitAmount;
    private double balance;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCash() {
        return Cash;
    }

    public void setCash(double cash) {
        Cash = cash;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getGrossSale() {
        return grossSale;
    }

    public void setGrossSale(double grossSale) {
        this.grossSale = grossSale;
    }

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }

    public int getNoOfTransactions() {
        return noOfTransactions;
    }

    public void setNoOfTransactions(int noOfTransactions) {
        this.noOfTransactions = noOfTransactions;
    }

    public double getAvgTotal() {
        return avgTotal;
    }

    public void setAvgTotal(double avgTotal) {
        this.avgTotal = avgTotal;
    }

    public double getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(double profitAmount) {
        this.profitAmount = profitAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
