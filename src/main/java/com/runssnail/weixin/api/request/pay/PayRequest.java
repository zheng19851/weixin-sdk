package com.runssnail.weixin.api.request.pay;

import com.runssnail.weixin.api.exception.ApiRuleException;
import com.runssnail.weixin.api.response.pay.PayResponse;

import java.util.Map;

/**
 * 微信支付请求
 * <p>
 * Created by zhengwei on 2016/3/24.
 */
public interface PayRequest<R extends PayResponse> {

    /**
     * api url
     *
     * @return api url
     */
    String getApiUrl();

    /**
     * 获取请求参数
     *
     * @return params
     */
    Map<String, Object> getParams();

    /**
     * 检查参数
     *
     * @throws ApiRuleException
     */
    void check() throws ApiRuleException;

    /**
     * Response class
     *
     * @return Response class
     */
    Class<R> getResponseClass();

    /**
     * 请求自己实现创建对于的响应对象
     *
     * @param responseBody 响应内容
     * @return
     */
    R buildResponse(Object responseBody);
}
