package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.DefaultWeixinApiClient;
import com.runssnail.weixin.api.common.Result;

/**
 * 直接请求微信api获取
 *
 * @author zhengwei
 */
public class SimpleAccessTokenManager extends AbstractAccessTokenManager {

    public SimpleAccessTokenManager(DefaultWeixinApiClient weixinApiClient) {
        super(weixinApiClient);
    }

    @Override
    public String getAccessToken() {
        return super.accessTokenService.getAccessToken();
    }

    @Override
    public Result<String> refresh() {
        Result<String> result = new Result<String>();
        String accessToken = accessTokenService.getAccessToken();

        return result.setSuccess(true).setResult(accessToken);
    }

}
