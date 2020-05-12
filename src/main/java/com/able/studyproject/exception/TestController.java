package com.able.studyproject.exception;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @param
 * @author jipeng
 * @date 2020-04-30 15:53
 */

public class TestController {

    @GetMapping("checkNotNull")
    public void checkNotNull(String licence) {
        ResponseEnum.LICENCE_NOT_FOUND.assertNotNull(licence);
    }
}

