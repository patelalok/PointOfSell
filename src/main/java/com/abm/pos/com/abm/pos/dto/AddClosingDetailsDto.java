package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */

public class AddClosingDetailsDto {

    int userId;
    String openDate;
    double intialAmount;
    double reportCash;
    double reportCredit;
    double closeCash;
    double closeCredit;
    String closeDate;
    double differenceCash;
    double differenceCredit;
    double totalDifference;
    double totalAmount;

    public double getIntialAmount() {
        return intialAmount;
    }

    public void setIntialAmount(double intialAmount) {
        this.intialAmount = intialAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
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

    public double getTotalDifference() {
        return totalDifference;
    }

    public void setTotalDifference(double totalDifference) {
        this.totalDifference = totalDifference;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }



}
