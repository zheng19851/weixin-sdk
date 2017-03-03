package com.runssnail.weixin.api.response;

import com.runssnail.weixin.api.constant.DataType;

/**
 * byte response
 * <p>
 * Created by zhengwei on 2017/3/2.
 */
public abstract class ByteResponse extends WeixinResponse {

    private static final long serialVersionUID = -8649831568326564786L;

    /**
     * 响应数据格式
     *
     * @return
     */
    public DataType getDataType() {
        return DataType.BYTE;
    }
}
