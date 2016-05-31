package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */

public class ClosingDetailsDto {

    private int registerId;
    private int userId;
    private String openDate;
    private double openAmount;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public double getOpenAmount() {
        return openAmount;
    }

    public void setOpenAmount(double openAmount) {
        this.openAmount = openAmount;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
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
