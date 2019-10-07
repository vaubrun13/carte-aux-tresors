package com.vaubrun.exception;

public class BadTreasureCountParameter extends Exception {
    public BadTreasureCountParameter() {
    }

    public BadTreasureCountParameter(String message) {
        super(message);
    }

    public BadTreasureCountParameter(String message, Throwable cause) {
        super(message, cause);
    }

    public BadTreasureCountParameter(Throwable cause) {
        super(cause);
    }

    protected BadTreasureCountParameter(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
