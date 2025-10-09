package com.example.springbootapp.model;

public enum MovementType {
    IN("IN"),
    OUT("OUT");

    private final String value;

    MovementType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
