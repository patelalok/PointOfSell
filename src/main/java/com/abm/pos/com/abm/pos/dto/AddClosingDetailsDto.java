package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */

public class AddClosingDetailsDto {

    int userId;
    String openDate;
    double openAmount;
    double reportCash;
    double reportCredit;
    double reportTotalAmount;
    double closeCash;
    double closeCredit;
    String closeDate;
    double closeTotalAmount;
    double differenceCash;
    double differenceCredit;
    double totalDifference;
    double totalBusinessAmount;


    public double getDifferenceCredit() {
        return differenceCredit;
    }

    public void setDifferenceCredit(double differenceCredit) {
        this.differenceCredit = differenceCredit;
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

    public double getOpenAmount() {
        return openAmount;
    }

    public void setOpenAmount(double openAmount) {
        this.openAmount = openAmount;
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

    public double getTotalDifference() {
        return totalDifference;
    }

    public void setTotalDifference(double totalDifference) {
        this.totalDifference = totalDifference;
    }

    public double getTotalBusinessAmount() {
        return totalBusinessAmount;
    }

    public void setTotalBusinessAmount(double totalBusinessAmount) {
        this.totalBusinessAmount = totalBusinessAmount;
    }




}
