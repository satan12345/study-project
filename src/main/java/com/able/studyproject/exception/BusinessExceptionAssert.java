package com.able.studyproject.exception;

import java.text.MessageFormat;

/**
 * @author jipeng
 * @date 2020-04-30 15:44
 * @description
 */
public interface BusinessExceptionAssert  extends IResponseEnum, Assert {
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
       String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }
}
