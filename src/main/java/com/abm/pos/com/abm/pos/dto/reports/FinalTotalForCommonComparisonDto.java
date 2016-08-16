package com.abm.pos.com.abm.pos.dto.reports;

/**
 * Created by asp5045 on 8/16/16.
 */
public class FinalTotalForCommonComparisonDto {

    private int totalQuantity;
    private double totalDiscount;
    private double totalSales;
    private double totalTax;
    private double totalProfit;
    private double totalMarkUp;
    private double totalPer;

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalMarkUp() {
        return totalMarkUp;
    }

    public void setTotalMarkUp(double totalMarkUp) {
        this.totalMarkUp = totalMarkUp;
    }

    public double getTotalPer() {
        return totalPer;
    }

    public void setTotalPer(double totalPer) {
        this.totalPer = totalPer;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }
}
