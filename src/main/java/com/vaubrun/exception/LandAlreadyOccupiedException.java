package com.vaubrun.exception;

public class LandAlreadyOccupiedException extends Exception {
    public LandAlreadyOccupiedException() {
    }

    public LandAlreadyOccupiedException(String message) {
        super(message);
    }

    public LandAlreadyOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LandAlreadyOccupiedException(Throwable cause) {
        super(cause);
    }

    protected LandAlreadyOccupiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
