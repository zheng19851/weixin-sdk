package com.runssnail.weixin.api.exception;

/**
 * ΢微信api异常
 * 
 * @author zhengwei
 *
 */
public class WeixinApiException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3696635528453296696L;

    public WeixinApiException(Throwable e) {
        super(e);
    }

    public WeixinApiException(String msg) {
        super(msg);
    }

    public WeixinApiException(String msg, Exception e) {
        super(msg, e);
    }
}
