package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/6/16.
 */
public class HourlyTransactionDto {


    private int hour;
    private double cost;
    private double retail;
    private double avgRetail;
    private int noOfTransactions;


    public double getAvgRetail() {
        return avgRetail;
    }

    public void setAvgRetail(double avgRetail) {
        this.avgRetail = avgRetail;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getNoOfTransactions() {
        return noOfTransactions;
    }

    public void setNoOfTransactions(int noOfTransactions) {
        this.noOfTransactions = noOfTransactions;
    }

    public double getRetail() {
        return retail;
    }

    public void setRetail(double retail) {
        this.retail = retail;
    }

}
