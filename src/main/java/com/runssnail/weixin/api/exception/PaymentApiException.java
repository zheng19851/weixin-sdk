package com.runssnail.weixin.api.exception;

/**
 * 微信支付异常
 *
 * Created by zhengwei on 16/6/8.
 */
public class PaymentApiException extends RuntimeException {

    private static final long serialVersionUID = -972494732895911289L;

    public PaymentApiException(Throwable e) {
        super(e);
    }

    public PaymentApiException(String msg) {
        super(msg);
    }

    public PaymentApiException(String msg, Exception e) {
        super(msg, e);
    }
}
