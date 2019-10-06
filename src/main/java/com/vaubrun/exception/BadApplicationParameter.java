package com.vaubrun.exception;

public class BadApplicationParameter extends Exception {
    public BadApplicationParameter() {
    }

    public BadApplicationParameter(String message) {
        super(message);
    }

    public BadApplicationParameter(String message, Throwable cause) {
        super(message, cause);
    }

    public BadApplicationParameter(Throwable cause) {
        super(cause);
    }

    protected BadApplicationParameter(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
