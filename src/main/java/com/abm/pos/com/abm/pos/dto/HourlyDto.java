package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/6/16.
 */
public class HourlyDto {

    private int hour;
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
   // private double cost;
   // private double retail;
    private int noOfTrans;

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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
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

    public int getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(int noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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






}
