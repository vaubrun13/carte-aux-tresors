package com.vaubrun.exception;

public class MapCreationException extends Exception {
    public MapCreationException() {
    }

    public MapCreationException(String message) {
        super(message);
    }

    public MapCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapCreationException(Throwable cause) {
        super(cause);
    }

    protected MapCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
