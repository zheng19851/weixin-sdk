package com.runssnail.weixin.api.exception;

/**
 * 微信sdk请求前置异常
 * 
 * @author zhengwei
 */
public class ApiRuleException extends ApiException {

    /**
     * 
     */
    private static final long serialVersionUID = -1202569302451253834L;

    public ApiRuleException(String msg) {
        super(msg);
    }

}
