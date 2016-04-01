package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.domain.jssdk.Config;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信服务
 *
 * Created by zhengwei on 2016/3/17.
 */
public interface WeiXinService {

    /**
     * 微信 appId
     *
     * @return
     */
    String getAppId();

    /**
     * 微信 appSecret
     *
     * @return
     */
    String getAppSecret();

    /**
     * 刷新access token
     */
    void refreshAccessToken();

    /**
     * 发送微信请求
     *
     * @param request 请求对象
     * @param <R> 响应对象
     * @return
     */
    <R extends Response> R execute(Request<R> request);

    /**
     * 获取微信js sdk配置信息
     *
     * @param url 网页url
     * @return
     */
    Config getJsSdkConfig(String url);

    /**
     * 刷新js sdk用到的ticket
     *
     * @return
     */
    String refreshTicket();
}
