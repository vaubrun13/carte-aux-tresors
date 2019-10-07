package com.vaubrun.exception;

public class BadApplicationParameterException extends Exception {
    public BadApplicationParameterException() {
    }

    public BadApplicationParameterException(String message) {
        super(message);
    }

    public BadApplicationParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadApplicationParameterException(Throwable cause) {
        super(cause);
    }

    protected BadApplicationParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
