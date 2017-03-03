package com.runssnail.weixin.api.result;

import com.runssnail.weixin.api.domain.BaseDomain;


/**
 * 通用结果
 * 
 * @author zhengwei
 * @see com.runssnail.weixin.api.domain.BaseDomain
 * @since 2014-2-17
 */
public class Result<T> extends BaseDomain {
    /**
     * 
     */
    private static final long serialVersionUID = -9075544910442561812L;

    /**
     * 是否处理成功标志
     */
    private boolean           success          = false;

    /**
     * 返回码
     */
    private String            resultCode;

    /**
     * 返回信息
     */
    private String            resultInfo;

    /**
     * 结果
     */
    private T                 result;

    public Result() {

    }

    public Result(boolean success, String resultCode, String resultInfo) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultInfo = resultInfo;
    }

    public Result(boolean success) {
        this.success = success;
    }

    /**
     * 设置错误信息
     * 
     * @param resultCode 返回码
     * @param resultInfo 返回信息
     * @return 对象本身
     */
    public Result<T> setError(String resultCode, String resultInfo) {
        this.success = false;
        this.resultCode = resultCode;
        this.resultInfo = resultInfo;
        return this;
    }

    public T getResult() {
        return result;
    }

    public Result<T> setResult(T result) {
        this.result = result;
        return this;
    }

    /**
     * 是否成功
     * 
     * @return 对象本身
     */
    public boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public Result<T> setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public Result<T> setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
        return this;
    }


}
