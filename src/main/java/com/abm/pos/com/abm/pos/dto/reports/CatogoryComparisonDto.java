package com.abm.pos.com.abm.pos.dto.reports;

/**
 * Created by asp5045 on 7/4/16.
 */
public class CatogoryComparisonDto {

    private String commanName;
    private int quantity;
    private double costPrice;
    private double retailPrice;
    private double profitAmount;
    private double markup;
    private double perOfTotalProfit;


    public String getCommanName() {
        return commanName;
    }

    public void setCommanName(String commanName) {
        this.commanName = commanName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
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

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }


}
