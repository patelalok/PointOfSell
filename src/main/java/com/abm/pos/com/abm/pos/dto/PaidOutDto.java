package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 6/29/16.
 */
public class PaidOutDto {

    private int paidOutId;
    private double paidOutAmount1;
    private double paidOutAmount2;
    private double paidOutAmount3;
    private String paidOutReason1;
    private String paidOutReason2;
    private String paidOutReason3;
    private String paidOutDate;
    private double sumPaidOut;

    public double getPaidOutAmount1() {
        return paidOutAmount1;
    }

    public void setPaidOutAmount1(double paidOutAmount1) {
        this.paidOutAmount1 = paidOutAmount1;
    }

    public double getPaidOutAmount2() {
        return paidOutAmount2;
    }

    public void setPaidOutAmount2(double paidOutAmount2) {
        this.paidOutAmount2 = paidOutAmount2;
    }

    public double getPaidOutAmount3() {
        return paidOutAmount3;
    }

    public void setPaidOutAmount3(double paidOutAmount3) {
        this.paidOutAmount3 = paidOutAmount3;
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

    public String getPaidOutReason1() {
        return paidOutReason1;
    }

    public void setPaidOutReason1(String paidOutReason1) {
        this.paidOutReason1 = paidOutReason1;
    }

    public String getPaidOutReason2() {
        return paidOutReason2;
    }

    public void setPaidOutReason2(String paidOutReason2) {
        this.paidOutReason2 = paidOutReason2;
    }

    public String getPaidOutReason3() {
        return paidOutReason3;
    }

    public void setPaidOutReason3(String paidOutReason3) {
        this.paidOutReason3 = paidOutReason3;
    }

    public double getSumPaidOut() {
        return sumPaidOut;
    }

    public void setSumPaidOut(double sumPaidOut) {
        this.sumPaidOut = sumPaidOut;
    }
}
