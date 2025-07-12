package com.example.ecommerce.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER,
    ADMIN;

    @Override
    public String getAuthority() {
        // Spring Security expects role names prefixed with "ROLE_"
        return "ROLE_" + name();
    }
}