package com.vaubrun.exception;

public class InvalidPositionException extends Exception {
    public InvalidPositionException() {
    }

    public InvalidPositionException(String message) {
        super(message);
    }

    public InvalidPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPositionException(Throwable cause) {
        super(cause);
    }

    protected InvalidPositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
