package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 6/29/16.
 */
public class PaidOutDto {

    private double paidOut1;
    private double paidOut2;
    private double paidOut3;

    private String reason1;
    private String reason2;
    private String reason3;

    private String paidOutDate;

    public String getPaidOutDate() {
        return paidOutDate;
    }

    public void setPaidOutDate(String paidOutDate) {
        this.paidOutDate = paidOutDate;
    }

    public String getReason1() {
        return reason1;
    }

    public void setReason1(String reason1) {
        this.reason1 = reason1;
    }

    public String getReason2() {
        return reason2;
    }

    public void setReason2(String reason2) {
        this.reason2 = reason2;
    }

    public String getReason3() {
        return reason3;
    }

    public void setReason3(String reason3) {
        this.reason3 = reason3;
    }



    public double getPaidOut1() {
        return paidOut1;
    }

    public void setPaidOut1(double paidOut1) {
        this.paidOut1 = paidOut1;
    }

    public double getPaidOut2() {
        return paidOut2;
    }

    public void setPaidOut2(double paidOut2) {
        this.paidOut2 = paidOut2;
    }

    public double getPaidOut3() {
        return paidOut3;
    }

    public void setPaidOut3(double paidOut3) {
        this.paidOut3 = paidOut3;
    }


}
