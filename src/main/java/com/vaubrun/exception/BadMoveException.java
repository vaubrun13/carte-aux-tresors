package com.vaubrun.exception;

public class BadMoveException extends Exception {
    public BadMoveException() {
    }

    public BadMoveException(String message) {
        super(message);
    }

    public BadMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadMoveException(Throwable cause) {
        super(cause);
    }

    protected BadMoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
