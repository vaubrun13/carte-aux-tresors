package com.vaubrun.exception;

public class InputFileFormatException extends Exception {
    public InputFileFormatException() {
    }

    public InputFileFormatException(String message) {
        super(message);
    }

    public InputFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputFileFormatException(Throwable cause) {
        super(cause);
    }

    protected InputFileFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
