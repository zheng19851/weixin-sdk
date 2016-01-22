package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.common.Lifecycle;
import com.runssnail.weixin.api.common.Result;

/**
 * 管理微信api凭证AccessToken
 *
 * @author zhengwei
 */
public interface AccessTokenManager extends Lifecycle {

    /**
     * 获取access_token
     */
    String getAccessToken();

    /**
     * 刷新
     *
     * @return
     */
    Result<String> refresh();
}
