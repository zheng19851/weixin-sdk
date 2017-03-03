package com.runssnail.weixin.api.response;

import com.runssnail.weixin.api.constant.DataType;
import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * weixin api response
 *
 * @author zhengwei
 */
public abstract class Response extends BaseDomain {

    /**
     *
     */
    private static final long serialVersionUID = -5684236406783615047L;

    /**
     * 响应body
     */
    private Object responseBody;

    /**
     * 响应数据格式
     *
     * @return
     */
    public abstract DataType getDataType();

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public abstract boolean isSuccess();

    /**
     * 设置错误码
     *
     * @param errcode
     */
    public abstract void setErrcode(String errcode);

    /**
     * 设置错误信息
     *
     * @param errmsg
     */
    public abstract void setErrmsg(String errmsg);

    /**
     * 错误码
     *
     * @return
     */
    public abstract String getErrcode();

    /**
     * 错误信息
     *
     * @return
     */
    public abstract String getErrmsg();

}
