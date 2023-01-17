package com.example.scm.domain.enums;

public enum RoleType {
    ROLE_RETAILER("Retailer"),
    ROLE_USER("User");

    private String name;

    private RoleType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
