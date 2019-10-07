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
                //Weird workaround as it seems to have a charset issue, some string comparison are failing but
                //they should not
                .filter(objectSeparator -> objectSeparator.value.equals(String.valueOf(val.charAt(0))))
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
