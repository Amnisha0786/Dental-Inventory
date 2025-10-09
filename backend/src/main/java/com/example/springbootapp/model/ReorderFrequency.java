package com.example.springbootapp.model;

public enum ReorderFrequency {
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly"),
    ONE_TIME("One-Time");

    private final String value;

    ReorderFrequency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
