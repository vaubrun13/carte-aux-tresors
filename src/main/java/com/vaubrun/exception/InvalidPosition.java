package com.vaubrun.exception;

public class InvalidPosition extends Exception {
    public InvalidPosition() {
    }

    public InvalidPosition(String message) {
        super(message);
    }

    public InvalidPosition(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPosition(Throwable cause) {
        super(cause);
    }

    protected InvalidPosition(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
