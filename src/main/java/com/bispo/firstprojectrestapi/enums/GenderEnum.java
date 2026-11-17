package com.bispo.firstprojectrestapi.enums;

public enum GenderEnum {
    MALE("Male"),
    FEMALE("Female");

    private final String displayName;

    GenderEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
