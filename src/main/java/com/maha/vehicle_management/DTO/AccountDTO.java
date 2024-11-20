package com.maha.vehicle_management.DTO;

import com.maha.vehicle_management.Models.enums.AccountRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AccountDTO {
    Long id;
    String email;
    String username;
    String name;
    AccountRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }
}
