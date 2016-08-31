package com.abm.pos.com.abm.pos.dto;

import java.util.List;

/**
 * Created by asp5045 on 6/30/16.
 */
public class MonthDto {

    private String date;
    private double credit;
    private double cash;
    private double check;
    private double tax;
    private double discount;
    private  double returnAmount;
    private double profit;
    private double marginPercentage;
    private double total;
    private double monthAvg;
    private double cost;
    private double retail;
    private int noOfTrans;
    private double balance;

    public int getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(int noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCheck() {
        return check;
    }

    public void setCheck(double check) {
        this.check = check;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getMarginPercentage() {
        return marginPercentage;
    }

    public void setMarginPercentage(double marginPercentage) {
        this.marginPercentage = marginPercentage;
    }

    public double getMonthAvg() {
        return monthAvg;
    }

    public void setMonthAvg(double monthAvg) {
        this.monthAvg = monthAvg;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getRetail() {
        return retail;
    }

    public void setRetail(double retail) {
        this.retail = retail;
    }

    public double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
