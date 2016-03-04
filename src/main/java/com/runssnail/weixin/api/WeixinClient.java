package com.runssnail.weixin.api;

import com.runssnail.weixin.api.common.Lifecycle;
import com.runssnail.weixin.api.exception.WeiXinApiException;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信api client，所有请求都从这里发起
 *
 * @author zhengwei
 */
public interface WeiXinClient extends Lifecycle {

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
     * @throws WeiXinApiException
     */
    <R extends Response> R execute(Request<R> req) throws WeiXinApiException;

    /**
     * 执行请求，accessToken必传
     *
     * @param req         请求
     * @param accessToken 接口凭证
     * @return 响应数据
     * @throws WeiXinApiException
     */
    <R extends Response> R execute(Request<R> req, String accessToken) throws WeiXinApiException;

}
