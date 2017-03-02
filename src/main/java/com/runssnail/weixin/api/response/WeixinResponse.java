package com.runssnail.weixin.api.response;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhengwei on 2017/3/2.
 */
public abstract class WeixinResponse extends Response {

    /**
     * 错误码
     */
    private String errcode;

    /**
     * 错误信息
     */
    private String errmsg;


    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return StringUtils.isBlank(this.errcode) || "0".equals(errcode);
    }

    public void setError(String errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }


}
