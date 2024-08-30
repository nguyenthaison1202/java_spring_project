package com.mockproject.group3.dto;

import com.mockproject.group3.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsersDTO {
    @NotNull(message = "Vui lòng điền đầy đủ thông tin")
    @Size(max = 200, message = "Qua 200 ky tu")
    private String full_name;
    private String password;

    // @NotNull
    @Email(message = "Vui lòng điền đầy đủ thông tin ")
    private String email;
    private String address;
    private String phone;
    private Role role;

    private boolean isBlocked;
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isIsBlocked() {
        return this.isBlocked;
    }

    public boolean getIsBlocked() {
        return this.isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

}
