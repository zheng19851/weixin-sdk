package com.runssnail.weixin.api.exception;

/**
 * 微信api异常
 * 
 * @author zhengwei
 *
 */
public class ApiException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3696635528453296696L;

    public ApiException(Throwable e) {
        super(e);
    }

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, Exception e) {
        super(msg, e);
    }
}
