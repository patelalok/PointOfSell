package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/13/16.
 */
public class FinalTotalForReportsDto {

    private double totalCredit;
    private double totalCash;
    private double totalCheck;
    private double totalTax;
    private double totalDiscount;
    private double grandTotal;
    private double totalProfit;
    private double totalReturn;
    private double avgMargin;

    public double getAvgMargin() {
        return avgMargin;
    }

    public void setAvgMargin(double avgMargin) {
        this.avgMargin = avgMargin;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public double getTotalCheck() {
        return totalCheck;
    }

    public void setTotalCheck(double totalCheck) {
        this.totalCheck = totalCheck;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getTotalReturn() {
        return totalReturn;
    }

    public void setTotalReturn(double totalReturn) {
        this.totalReturn = totalReturn;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }


}
