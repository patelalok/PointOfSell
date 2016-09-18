package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 8/8/16.
 */
public class UserClockInDto {

    private int clockInId;
    private String username;
    private String password;
    private String clockInTime;
    private String clockOutTime;
    private String noOfhours;
    private double horlyRate;
    private String date;
    private double userCommission;
    private double totalAmount;

    public int getClockInId() {
        return clockInId;
    }

    public void setClockInId(int clockInId) {
        this.clockInId = clockInId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(String clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getNoOfhours() {
        return noOfhours;
    }

    public void setNoOfhours(String noOfhours) {
        this.noOfhours = noOfhours;
    }

    public double getHorlyRate() {
        return horlyRate;
    }

    public void setHorlyRate(double horlyRate) {
        this.horlyRate = horlyRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUserCommission() {
        return userCommission;
    }

    public void setUserCommission(double userCommission) {
        this.userCommission = userCommission;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
