package com.runssnail.weixin.api.request.payment;

import com.runssnail.weixin.api.request.PostRequest;
import com.runssnail.weixin.api.response.payment.CreatePrepayOrderResponse;

import java.util.Map;

/**
 * 创建预支付单请求，这里参数用map保存
 *
 * @author zhengwei
 */
public class CreatePrepayOrder2Request extends PostRequest<CreatePrepayOrderResponse> {

    /**
     *
     */
    private static final long   serialVersionUID = -8543825581250399019L;

    private Map<String, Object> params           = null;

    public CreatePrepayOrder2Request(Map params) {
        this.params = params;
    }

    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/pay/unifiedorder";
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public Class<CreatePrepayOrderResponse> getResponseClass() {
        return CreatePrepayOrderResponse.class;
    }

}
