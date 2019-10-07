package com.vaubrun.board;


import java.util.Arrays;

public enum Orientation {
    SOUTH("S"), WEST("O"), NORTH("N"), EAST("E");
    private String value;

    Orientation(String value) {
        this.value = value;
    }

    public static Orientation fromValue(String val) {
        return Arrays.stream(Orientation.values())
                //Weird workaround as it seems to have a charset issue, some string comparison are failing but
                //they should not
                .filter(orientation -> orientation.value.equals(String.valueOf(val.charAt(0))))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(Orientation.class, val));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
