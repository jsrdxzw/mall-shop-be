package com.jsrdxzw.mallshopbe.exception;

import lombok.Getter;

/**
 * @author xuzhiwei
 * @date 2021-02-01
 */
@Getter
public class HttpException extends RuntimeException {
    protected Integer code;
    protected Integer httpStatusCode = 500;
}
