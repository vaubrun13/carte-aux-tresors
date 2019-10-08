package com.vaubrun.model;

import java.util.Arrays;

public enum Movement {
    MOVE_FORWARD("A"), TURN_LEFT("G"), TURN_RIGHT("D");

    private String value;

    Movement(String value) {
        this.value = value;
    }

    public static Movement fromValue(String val) {
        return Arrays.stream(Movement.values())
                .filter(objectSeparator -> objectSeparator.value.equals(val))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(Movement.class, val));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
