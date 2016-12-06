package com.runssnail.weixin.api.service;

/**
 * 微信Access Token服务
 *
 * Created by zhengwei on 2016/3/17.
 */
public interface AccessTokenService {

    /**
     * 获取AccessToken
     *
     * @return
     */
    String getAccessToken();

    /**
     * 刷新AccessToken
     *
     * @return 老的AccessToken
     */
    String refresh();

    /**
     * 刷新AccessToken，并且返回新的AccessToken
     *
     * @return 新的AccessToken
     */
    String refreshAndGet();

}
