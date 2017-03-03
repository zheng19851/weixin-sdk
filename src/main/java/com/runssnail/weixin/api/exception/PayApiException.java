package com.runssnail.weixin.api.exception;

/**
 * 微信支付异常
 *
 * Created by zhengwei on 16/6/8.
 */
public class PayApiException extends RuntimeException {

    private static final long serialVersionUID = -972494732895911289L;

    public PayApiException(Throwable e) {
        super(e);
    }

    public PayApiException(String msg) {
        super(msg);
    }

    public PayApiException(String msg, Exception e) {
        super(msg, e);
    }
}
