package com.runssnail.weixin.api.response;

import com.runssnail.weixin.api.common.DataType;

/**
 * json response
 *
 * @author zhengwei
 */
public abstract class JSONResponse extends WeixinResponse {

    /**
     *
     */
    private static final long serialVersionUID = 3636184130748220979L;

    /**
     * 响应数据格式
     *
     * @return
     */
    public DataType getDataType() {
        return DataType.JSON;
    }

}
