package com.runssnail.weixin.api.response;

import org.apache.commons.lang.StringUtils;

/**
 * josn response
 *
 * @author zhengwei
 */
public abstract class JSONResponse extends Response {

    /**
     *
     */
    private static final long serialVersionUID = 3636184130748220979L;

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
