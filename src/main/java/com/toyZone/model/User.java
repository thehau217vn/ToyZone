package com.toyZone.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.toyZone.dto.UserDto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Entity
@Table(name = "`user`")
public class User extends Abstract {
    @Column(name = "fullname", columnDefinition = "nvarchar(255)")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "otpCode")
    private String otpCode;

    @Column(name = "verify")
    private boolean verify;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public UserDto convertToDto() {
        UserDto dto = new UserDto();
        dto.setId(getId());
        dto.setFullName(getFullName());
        dto.setEmail(getEmail());
        dto.setPhone(getPhone());
        dto.setAccount(getAccount());
        dto.setCreatedDate(getUpdatedAt());
        dto.setPassword(getPassword());
        dto.setOtpCode(getOtpCode());
        dto.setVerify(isVerify());
        if (role != null) {
            dto.setRoleId(getRole().getId());
        }
        return dto;
    }

    public User convertToEntity(UserDto userDto) {
        setAccount(userDto.getAccount());
        setPassword(userDto.getPassword());
        setEmail(userDto.getEmail());
        setFullName(userDto.getFullName());
        setPhone(userDto.getPhone());
        setPassword(userDto.getPassword());
        setOtpCode(userDto.getOtpCode());
        setVerify(userDto.isVerify());
        Role role = new Role();
        role.setId(userDto.getRoleId());
        setRole(role);
        return this;
    }

    public User() {
        super();
    }


}
