package com.jsrdxzw.mallshopbe.exception;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public class ParameterException extends HttpException {
    public ParameterException(int code) {
        this.code = code;
        this.httpStatusCode = 400;
    }
}
