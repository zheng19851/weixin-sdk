package com.runssnail.weixin.api.exception;

/**
 * 微信api异常
 * 
 * @author zhengwei
 *
 */
public class WeiXinApiException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3696635528453296696L;

    public WeiXinApiException(Throwable e) {
        super(e);
    }

    public WeiXinApiException(String msg) {
        super(msg);
    }

    public WeiXinApiException(String msg, Exception e) {
        super(msg, e);
    }
}
