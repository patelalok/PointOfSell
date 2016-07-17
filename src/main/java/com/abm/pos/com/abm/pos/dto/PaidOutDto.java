package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 6/29/16.
 */
public class PaidOutDto {

    private int paidOutId;
    private double paidOutAmount;
    private String reason;
    private String paidOutDate;
    private double sumOfPaidOut;

    public double getSumOfPaidOut() {
        return sumOfPaidOut;
    }

    public void setSumOfPaidOut(double sumOfPaidOut) {
        this.sumOfPaidOut = sumOfPaidOut;
    }

    public double getPaidOutAmount() {
        return paidOutAmount;
    }

    public void setPaidOutAmount(double paidOutAmount) {
        this.paidOutAmount = paidOutAmount;
    }

    public String getPaidOutDate() {
        return paidOutDate;
    }

    public void setPaidOutDate(String paidOutDate) {
        this.paidOutDate = paidOutDate;
    }

    public int getPaidOutId() {
        return paidOutId;
    }

    public void setPaidOutId(int paidOutId) {
        this.paidOutId = paidOutId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
