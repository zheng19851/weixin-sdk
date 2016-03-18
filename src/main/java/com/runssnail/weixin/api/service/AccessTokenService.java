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
     * @return
     */
    String refresh();

}
