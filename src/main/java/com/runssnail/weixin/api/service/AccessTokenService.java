package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.result.Result;
import com.runssnail.weixin.api.domain.token.RefreshTokenDO;

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
     * @return 新的AccessToken
     */
    String refresh();

    /**
     * 刷新AccessToken，并且返回新的AccessToken
     *
     * @return 新的AccessToken
     */
    Result<RefreshTokenDO> refreshAndGet();

}
