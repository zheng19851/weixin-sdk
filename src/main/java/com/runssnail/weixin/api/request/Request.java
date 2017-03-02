package com.runssnail.weixin.api.request;

import com.runssnail.weixin.api.common.RequestMethod;
import com.runssnail.weixin.api.exception.ApiRuleException;
import com.runssnail.weixin.api.response.Response;

import java.util.Map;

/**
 * 请求接口
 *
 * @author zhengwei
 * @param <R>
 */
public interface Request<R extends Response> {

    /**
     * http 请求方式
     *
     * @return RequestMethod
     */
    RequestMethod getMethod();

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
