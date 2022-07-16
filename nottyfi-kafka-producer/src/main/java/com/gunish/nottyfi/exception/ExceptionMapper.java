package com.gunish.nottyfi.exception;

public class ExceptionMapper extends RuntimeException {
    public ExceptionMapper(String message,Throwable throwable)
    {
        super(message,throwable);
    }
    public ExceptionMapper(String message)
    {
        super(message);
    }
}
