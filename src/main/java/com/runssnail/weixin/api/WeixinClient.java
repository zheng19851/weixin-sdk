package com.runssnail.weixin.api;

import com.runssnail.weixin.api.common.Lifecycle;
import com.runssnail.weixin.api.exception.ApiException;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信api client，所有请求都从这里发起
 *
 * @author zhengwei
 */
public interface WeixinClient extends Lifecycle {

    /**
     * appId appId
     *
     * @return appId
     */
    String getAppId();

    /**
     * appSecret
     *
     * @return 返回appSecret
     */
    String getAppSecret();

    /**
     * 请求
     *
     * @param req 请求对象
     * @param <R>
     * @return
     * @throws ApiException
     */
    <R extends Response> R execute(Request<R> req) throws ApiException;

    /**
     * 执行请求，accessToken必传
     *
     * @param req         请求
     * @param accessToken 接口凭证
     * @return 响应数据
     * @throws ApiException
     */
    <R extends Response> R execute(Request<R> req, String accessToken) throws ApiException;

}
