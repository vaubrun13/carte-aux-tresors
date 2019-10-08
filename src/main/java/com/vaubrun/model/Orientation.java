package com.vaubrun.model;


import java.util.Arrays;

public enum Orientation {
    SOUTH("S"), WEST("O"), NORTH("N"), EAST("E");
    private String value;

    Orientation(String value) {
        this.value = value;
    }

    public static Orientation fromValue(String val) {
        return Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.value.equals(val))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(Orientation.class, val));
    }

    public String getValue() {
        return value;
    }
}
