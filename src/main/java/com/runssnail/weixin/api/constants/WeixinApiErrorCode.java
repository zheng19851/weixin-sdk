package com.runssnail.weixin.api.constants;

/**
 * 错误码
 *
 * @author zhengwei
 * @date 2014-2-18
 */
public enum WeixinApiErrorCode {

    ACCESS_TOKEN_ERROR("40001", "获取access_token时AppSecret错误，或者access_token无效");

    private String errorCode;

    private String errorInfo;

    WeixinApiErrorCode(String errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 是否access token 错误
     *
     * @param errCode 错误码
     * @return ture or false
     */
    public static boolean isAccessTokenError(String errCode) {
        return ACCESS_TOKEN_ERROR.getErrorCode().equalsIgnoreCase(errCode);
    }

}
