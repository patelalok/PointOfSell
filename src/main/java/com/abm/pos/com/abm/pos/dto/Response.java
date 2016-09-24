package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 6/6/16.
 */

public class Response {

    private int statusCode;
    private String statusMessege;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessege() {
        return statusMessege;
    }

    public void setStatusMessege(String statusMessege) {
        this.statusMessege = statusMessege;
    }
}
