package com.jsrdxzw.mallshopbe.exception;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
public class ServerErrorException extends HttpException {
    public ServerErrorException(int code) {
        this.httpStatusCode = 500;
        this.code = code;
    }
}
