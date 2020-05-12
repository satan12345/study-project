package com.able.studyproject.exception;

/**
 * @param
 * @author jipeng
 * @date 2020-04-30 15:30
 */

public class BaseException extends RuntimeException {
    private IResponseEnum responseEnum;
    private Object[] args;
    private String message;
    private Throwable cause;

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable throwable) {
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
        this.cause=throwable;
    }


}

