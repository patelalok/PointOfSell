package com.abm.pos.com.abm.pos.dto.reports;

import com.abm.pos.com.abm.pos.dto.FinalTotalForReportsDto;

import java.util.List;

/**
 * Created by asp5045 on 7/10/16.
 */
public class YearlyDto {

    private String monthName;
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
    private double avgBasketSize;

    private FinalTotalForReportsDto finalTotalForReportsDtos;

    public FinalTotalForReportsDto getFinalTotalForReportsDtos() {
        return finalTotalForReportsDtos;
    }

    public void setFinalTotalForReportsDtos(FinalTotalForReportsDto finalTotalForReportsDtos) {
        this.finalTotalForReportsDtos = finalTotalForReportsDtos;
    }

    public double getAvgBasketSize() {
        return avgBasketSize;
    }

    public void setAvgBasketSize(double avgBasketSize) {
        this.avgBasketSize = avgBasketSize;
    }

    public int getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(int noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRetail() {
        return retail;
    }

    public void setRetail(double retail) {
        this.retail = retail;
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

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
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
