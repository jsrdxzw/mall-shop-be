package com.jsrdxzw.mallshopbe.exception;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public class ForbiddenException extends HttpException {
    public ForbiddenException(int code) {
        this.code = code;
        this.httpStatusCode = 403;
    }
}
