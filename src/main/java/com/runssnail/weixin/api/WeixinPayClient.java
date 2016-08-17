package com.runssnail.weixin.api;

import com.runssnail.weixin.api.common.Lifecycle;
import com.runssnail.weixin.api.exception.ApiException;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信支付api client
 *
 * <p/>
 * Created by zhengwei on 2015/11/6.
 */
public interface WeixinPayClient extends Lifecycle {

    /**
     * appId appId
     *
     * @return appId
     */
    String getAppId();

    /**
     * 商户号
     *
     * @return
     */
    String getMchId();

    /**
     * 微信支付签名秘钥
     *
     * @return
     */
    String getPaySignKey();

    /**
     * 请求
     *
     * @param req 请求对象
     * @param <R>
     * @return
     * @throws ApiException
     */
    <R extends Response> R execute(Request<R> req) throws ApiException;
}
