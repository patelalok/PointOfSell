package com.abm.pos.com.abm.pos.dto.reports;

/**
 * Created by asp5045 on 7/4/16.
 */
public class CommonComparisonDto {

    private String commanName;
    private int quantity;
    private double salesTotal;
    private double avgSalesTotal;
    private double profitAmount;
    private double markup;
    private double discount;
    private double perOfTotalProfit;


    public String getCommanName() {
        return commanName;
    }

    public void setCommanName(String commanName) {
        this.commanName = commanName;
    }

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }

    public double getPerOfTotalProfit() {
        return perOfTotalProfit;
    }

    public void setPerOfTotalProfit(double perOfTotalProfit) {
        this.perOfTotalProfit = perOfTotalProfit;
    }

    public double getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(double profitAmount) {
        this.profitAmount = profitAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAvgSalesTotal() {
        return avgSalesTotal;
    }

    public void setAvgSalesTotal(double avgSalesTotal) {
        this.avgSalesTotal = avgSalesTotal;
    }

    public double getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(double salesTotal) {
        this.salesTotal = salesTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
