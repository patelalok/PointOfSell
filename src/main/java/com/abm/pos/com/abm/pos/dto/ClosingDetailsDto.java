package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/20/16.
 */

public class ClosingDetailsDto {

    private int registerId;
    private int userIdClose;

    private double reportCash;
    private double reportCredit;
    private double reportCheck;
    private double reportDebit;
    private double reportTotalAmount;

    private double closeCash;
    private double closeCredit;
    private double closeCheck;
    private double closeDebit;
    private String closeDate;
    private double closeTotalAmount;

    private double differenceCash;
    private double differenceCredit;
    private double differenceCheck;
    private double differenceDebit;

    private double totalDifference;
    private double totalBusinessAmount;
    private double totalTax;
    private double totalDiscount;
    private double totalProfit;
    private double totalMarkup;

    private double bankDeposit;
    private double customerBalance;
    private double commission;
    private double previousBalance;

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public int getUserIdClose() {
        return userIdClose;
    }

    public void setUserIdClose(int userIdClose) {
        this.userIdClose = userIdClose;
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

    public double getReportCheck() {
        return reportCheck;
    }

    public void setReportCheck(double reportCheck) {
        this.reportCheck = reportCheck;
    }

    public double getReportDebit() {
        return reportDebit;
    }

    public void setReportDebit(double reportDebit) {
        this.reportDebit = reportDebit;
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

    public double getCloseCheck() {
        return closeCheck;
    }

    public void setCloseCheck(double closeCheck) {
        this.closeCheck = closeCheck;
    }

    public double getCloseDebit() {
        return closeDebit;
    }

    public void setCloseDebit(double closeDebit) {
        this.closeDebit = closeDebit;
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

    public double getDifferenceCheck() {
        return differenceCheck;
    }

    public void setDifferenceCheck(double differenceCheck) {
        this.differenceCheck = differenceCheck;
    }

    public double getDifferenceDebit() {
        return differenceDebit;
    }

    public void setDifferenceDebit(double differenceDebit) {
        this.differenceDebit = differenceDebit;
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

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
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

    public double getTotalMarkup() {
        return totalMarkup;
    }

    public void setTotalMarkup(double totalMarkup) {
        this.totalMarkup = totalMarkup;
    }

    public double getBankDeposit() {
        return bankDeposit;
    }

    public void setBankDeposit(double bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public double getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(double previousBalance) {
        this.previousBalance = previousBalance;
    }
}
