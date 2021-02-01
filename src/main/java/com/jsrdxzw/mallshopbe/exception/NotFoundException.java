package com.jsrdxzw.mallshopbe.exception;

/**
 * @author xuzhiwei
 * @date 2021-02-01
 */
public class NotFoundException extends HttpException {
    public NotFoundException(int code) {
        this.code = code;
        this.httpStatusCode = 404;
    }
}
