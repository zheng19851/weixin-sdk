package com.runssnail.weixin.api.exception;

/**
 * 签名错误
 * 
 * @author zhengwei
 */
public class SignException extends ApiException {

    /**
     * 
     */
    private static final long serialVersionUID = -1506662291048322297L;

    public SignException(String msg) {
        super(msg);
    }

}
