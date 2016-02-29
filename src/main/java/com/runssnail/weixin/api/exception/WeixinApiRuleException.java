package com.runssnail.weixin.api.exception;

/**
 * 微信sdk请求前置异常
 * 
 * @author zhengwei
 */
public class WeiXinApiRuleException extends WeiXinApiException {

    /**
     * 
     */
    private static final long serialVersionUID = -1202569302451253834L;

    public WeiXinApiRuleException(String msg) {
        super(msg);
    }

}
