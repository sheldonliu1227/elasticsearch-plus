package com.sheldon.elasticsearch.core.exception;

public class ClassInaccuracyException extends Exception {
    public ClassInaccuracyException(String message) {
        super(message);
    }

    public ClassInaccuracyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassInaccuracyException(Throwable cause) {
        super(cause);
    }

    public ClassInaccuracyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
