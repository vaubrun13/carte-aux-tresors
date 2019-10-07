package com.vaubrun.exception;

public class BadPositionParameter extends Exception {
    public BadPositionParameter() {
    }

    public BadPositionParameter(String message) {
        super(message);
    }

    public BadPositionParameter(String message, Throwable cause) {
        super(message, cause);
    }

    public BadPositionParameter(Throwable cause) {
        super(cause);
    }

    protected BadPositionParameter(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
