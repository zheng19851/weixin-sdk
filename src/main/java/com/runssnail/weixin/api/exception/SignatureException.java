package com.runssnail.weixin.api.exception;

/**
 * 签名错误
 * 
 * @author zhengwei
 */
public class SignatureException extends ApiException {

    /**
     * 
     */
    private static final long serialVersionUID = -1506662291048322297L;

    public SignatureException(String msg) {
        super(msg);
    }

}
