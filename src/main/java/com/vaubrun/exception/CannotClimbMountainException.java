package com.vaubrun.exception;

public class CannotClimbMountainException extends Exception {
    public CannotClimbMountainException() {
    }

    public CannotClimbMountainException(String message) {
        super(message);
    }

    public CannotClimbMountainException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotClimbMountainException(Throwable cause) {
        super(cause);
    }

    protected CannotClimbMountainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
