package com.example.real_store.controller.ex;

public class FileUpLoadException extends RuntimeException{
    public FileUpLoadException() {
        super();
    }

    public FileUpLoadException(String message) {
        super(message);
    }

    public FileUpLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUpLoadException(Throwable cause) {
        super(cause);
    }

    protected FileUpLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
