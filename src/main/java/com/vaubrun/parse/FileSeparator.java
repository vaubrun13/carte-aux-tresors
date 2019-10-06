package com.vaubrun.parse;


import java.util.Arrays;

public enum FileSeparator {
    MAP("C"), MOUNTAIN("M"), TREASURE("T"), ADVENTURER("A"), INFO_SEPARATOR("-");

    private String value;

    FileSeparator(String value) {
        this.value = value;
    }

    public static FileSeparator fromValue(String val) {
        return Arrays.stream(FileSeparator.values())
                //Weird workaround as it seems to have a charset issue, some string comparison are failing but
                //they should not
                .filter(fileSeparator -> fileSeparator.value.equals(String.valueOf(val.charAt(0))))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(FileSeparator.class, val));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
