package com.abm.pos.com.abm.pos.dto;

import java.util.List;

/**
 * Created by asp5045 on 6/30/16.
 */
public class MonthDto {

    private String date;
    private double sumCash;
    private double sumCredit;
    private double total;
    private double sumTax;
    private double DailyAvgForMonth;
    private double discount;



    public double getDailyAvgForMonth() {
        return DailyAvgForMonth;
    }

    public void setDailyAvgForMonth(double dailyAvgForMonth) {
        DailyAvgForMonth = dailyAvgForMonth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSumCash() {
        return sumCash;
    }

    public void setSumCash(double sumCash) {
        this.sumCash = sumCash;
    }

    public double getSumCredit() {
        return sumCredit;
    }

    public void setSumCredit(double sumCredit) {
        this.sumCredit = sumCredit;
    }

    public double getSumTax() {
        return sumTax;
    }

    public void setSumTax(double sumTax) {
        this.sumTax = sumTax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }




}
