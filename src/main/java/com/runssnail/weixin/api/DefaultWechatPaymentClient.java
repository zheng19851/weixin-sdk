package com.runssnail.weixin.api;

import com.runssnail.weixin.api.internal.support.WeixinPayResponseHelper;
import com.runssnail.weixin.api.internal.utils.XmlTool;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.response.payment.PaymentResponse;

import java.util.Map;

/**
 * 默认的微信支付api client
 *
 * Created by zhengwei on 2015/11/6.
 */
public class DefaultWechatPaymentClient extends DefaultWeiXinClient implements WechatPaymentClient {

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 微信支付签名秘钥
     */
    private String paySignKey;

    public DefaultWechatPaymentClient(String appId, String mchId, String paySignKey) {
        super(appId, null);

        this.mchId = mchId;
        this.paySignKey = paySignKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPaySignKey() {
        return paySignKey;
    }

    public void setPaySignKey(String paySignKey) {
        this.paySignKey = paySignKey;
    }

    /**
     * 将post参数转换成字符串
     *
     * @param params
     * @return
     */
    @Override
    protected String buildPostParams(Map<String, Object> params) {
        return XmlTool.toXml(params);
    }

    @Override
    protected <R extends Response> R buildResponse(String result, Request<R> req) {
        return WeixinPayResponseHelper.getObjectFromXml(result, req.getResponseClass());
    }

    /**
     * 校验响应数据
     *
     * @param res
     * @param <R>
     */
    protected <R extends Response> void checkResponse(R res) {
        ((PaymentResponse) res).check(this.paySignKey);
    }
}
