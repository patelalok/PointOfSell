package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 8/1/16.
 */
public class UserLogin {

    private boolean isValidUser;
    private String userRole;
    private int userId;

    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }

    public String getUserRole() {
        return userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
