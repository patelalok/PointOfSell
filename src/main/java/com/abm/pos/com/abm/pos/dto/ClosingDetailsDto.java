package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */

public class ClosingDetailsDto {

    private int registerId;
    private int userIdClose;
    private double reportCash;
    private double reportCredit;
    private double reportTotalAmount;
    private double closeCash;
    private double closeCredit;
    private String closeDate;
    private double closeTotalAmount;
    private double differenceCash;
    private double differenceCredit;
    private double totalDifference;
    private double totalBusinessAmount;
    private double totalTax;
    private double totalDiscount;
    private double totalProfit;
    private double totalMarkup;
    private String registerStatus;


    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public int getUserIdClose() {
        return userIdClose;
    }

    public void setUserIdClose(int userIdClose) {
        this.userIdClose = userIdClose;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalMarkup() {
        return totalMarkup;
    }

    public void setTotalMarkup(double totalMarkup) {
        this.totalMarkup = totalMarkup;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getCloseCash() {
        return closeCash;
    }

    public void setCloseCash(double closeCash) {
        this.closeCash = closeCash;
    }

    public double getCloseCredit() {
        return closeCredit;
    }

    public void setCloseCredit(double closeCredit) {
        this.closeCredit = closeCredit;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public double getCloseTotalAmount() {
        return closeTotalAmount;
    }

    public void setCloseTotalAmount(double closeTotalAmount) {
        this.closeTotalAmount = closeTotalAmount;
    }

    public double getDifferenceCash() {
        return differenceCash;
    }

    public void setDifferenceCash(double differenceCash) {
        this.differenceCash = differenceCash;
    }

    public double getDifferenceCredit() {
        return differenceCredit;
    }

    public void setDifferenceCredit(double differenceCredit) {
        this.differenceCredit = differenceCredit;
    }

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public double getReportCash() {
        return reportCash;
    }

    public void setReportCash(double reportCash) {
        this.reportCash = reportCash;
    }

    public double getReportCredit() {
        return reportCredit;
    }

    public void setReportCredit(double reportCredit) {
        this.reportCredit = reportCredit;
    }

    public double getReportTotalAmount() {
        return reportTotalAmount;
    }

    public void setReportTotalAmount(double reportTotalAmount) {
        this.reportTotalAmount = reportTotalAmount;
    }

    public double getTotalBusinessAmount() {
        return totalBusinessAmount;
    }

    public void setTotalBusinessAmount(double totalBusinessAmount) {
        this.totalBusinessAmount = totalBusinessAmount;
    }

    public double getTotalDifference() {
        return totalDifference;
    }

    public void setTotalDifference(double totalDifference) {
        this.totalDifference = totalDifference;
    }







}
