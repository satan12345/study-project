package com.able.studyproject.exception;

/**
 * @param
 * @author jipeng
 * @date 2020-04-30 15:31
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}




