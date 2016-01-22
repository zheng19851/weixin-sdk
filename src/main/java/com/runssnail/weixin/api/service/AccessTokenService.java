package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.common.Lifecycle;

/**
 * AccessToken管理服务
 *
 * @author zhengwei
 * @date 2014-2-14
 */
public interface AccessTokenService extends Lifecycle {

    /**
     * 公众号access token
     *
     * @return access token
     */
    String getAccessToken();

}
