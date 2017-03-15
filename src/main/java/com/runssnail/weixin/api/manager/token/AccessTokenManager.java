package com.runssnail.weixin.api.manager.token;

import com.runssnail.weixin.api.domain.token.TokenDO;
import com.runssnail.weixin.api.manager.ValueManager;

/**
 * 微信Access Token服务
 * <p>
 * Created by zhengwei on 2016/3/17.
 */
public interface AccessTokenManager extends ValueManager<TokenDO> {

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


}
