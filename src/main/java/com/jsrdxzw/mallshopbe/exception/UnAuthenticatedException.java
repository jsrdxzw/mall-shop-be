package com.jsrdxzw.mallshopbe.exception;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
public class UnAuthenticatedException extends HttpException {
    public UnAuthenticatedException(int code) {
        this.httpStatusCode = 401;
        this.code = code;
    }
}
