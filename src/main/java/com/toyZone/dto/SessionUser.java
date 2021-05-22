package com.toyZone.dto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public class SessionUser {
    private Integer userId;
    private String fullName;
    private String role;
    private boolean checkLogin = false;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }


}
