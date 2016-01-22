package com.runssnail.weixin.api.exception;

/**
 * 微信sdk请求前置异常
 * 
 * @author zhengwei
 */
public class WeixinApiRuleException extends WeixinApiException {

    /**
     * 
     */
    private static final long serialVersionUID = -1202569302451253834L;

    public WeixinApiRuleException(String msg) {
        super(msg);
    }

}
