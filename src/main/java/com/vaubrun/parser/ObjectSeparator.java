package com.vaubrun.parser;


import java.util.Arrays;

public enum ObjectSeparator {
    MAP("C"), MOUNTAIN("M"), TREASURE("T"), ADVENTURER("A"), INFO_SEPARATOR("-"), COMMENT("#");

    private String value;

    ObjectSeparator(String value) {
        this.value = value;
    }

    public static ObjectSeparator fromValue(String val) {
        return Arrays.stream(ObjectSeparator.values())
                .filter(objectSeparator -> objectSeparator.value.equals(val))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(ObjectSeparator.class, val));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
