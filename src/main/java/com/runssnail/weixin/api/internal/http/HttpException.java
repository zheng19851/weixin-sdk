package com.runssnail.weixin.api.internal.http;

/**
 * Created by zhengwei on 16/6/8.
 */
public class HttpException extends RuntimeException {
    public HttpException(Throwable t) {
        super(t);
    }
}
