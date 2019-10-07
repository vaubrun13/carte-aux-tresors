package com.vaubrun.exception;

public class BadPositionParameterException extends Exception {
    public BadPositionParameterException() {
    }

    public BadPositionParameterException(String message) {
        super(message);
    }

    public BadPositionParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadPositionParameterException(Throwable cause) {
        super(cause);
    }

    protected BadPositionParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
