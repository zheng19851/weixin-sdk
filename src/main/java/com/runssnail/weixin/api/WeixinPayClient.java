package com.runssnail.weixin.api;

import com.runssnail.weixin.api.common.Lifecycle;
import com.runssnail.weixin.api.exception.PayApiException;
import com.runssnail.weixin.api.request.pay.PayRequest;
import com.runssnail.weixin.api.response.pay.PayResponse;

/**
 * 微信支付api client
 * <p>
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
     * @throws PayApiException
     */
    <R extends PayResponse> R execute(PayRequest<R> req) throws PayApiException;
}
