package com.example.springbootapp.model;

public enum Role {
    ADMIN("admin"),
    STAFF("staff");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
