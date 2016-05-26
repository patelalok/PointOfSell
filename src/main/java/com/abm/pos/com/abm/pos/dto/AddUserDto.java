package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 5/25/16.
 */
public class AddUserDto {

    private String username;
    private String password;
    private String userRole;
    private String createdDate;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }



}
