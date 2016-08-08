package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 8/8/16.
 */
public class UserClockInDto {

    private int clockInId;
    private String username;
    private String clockInTime;
    private String clockOutTime;
    private String noOfhours;
    private String date;

    public int getClockInId() {
        return clockInId;
    }

    public void setClockInId(int clockInId) {
        this.clockInId = clockInId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoOfhours() {
        return noOfhours;
    }

    public void setNoOfhours(String noOfhours) {
        this.noOfhours = noOfhours;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
