package com.toyZone.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public class AccountDto {
    @NotBlank(message = "Tên tài khoản không được bỏ trống")
    private String account;
    @Size(min = 8, message = "Mật khẩu phải nhiều hơn 8 kí tự")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
