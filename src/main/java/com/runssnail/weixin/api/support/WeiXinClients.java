package com.runssnail.weixin.api.support;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.manager.token.AccessTokenManager;
import com.runssnail.weixin.api.manager.token.MemoryAccessTokenManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * WeiXinApiClient创建帮助类
 *
 * @author zhengwei
 * @since 1.0-SNAPSHOT
 */
public class WeiXinClients {

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return RetryWeiXinApiClient
     */
    public static RetryWeiXinClient buildRetryWeiXinClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        DefaultWeixinClient weiXinClient = buildDefaultWeiXinClient(appId, appSecret);

        return buildRetryWeiXinClient(weiXinClient);
    }

    /**
     * 创建默认的WeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return DefaultWeiXinApiClient
     */
    public static DefaultWeixinClient buildDefaultWeiXinClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        DefaultWeixinClient defaultWeixinClient = new DefaultWeixinClient(appId, appSecret);

        return defaultWeixinClient;
    }

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param defaultWeixinClient 默认的WeiXinApiClient
     * @return RetryWeiXinApiClient
     */
    public static RetryWeiXinClient buildRetryWeiXinClient(DefaultWeixinClient defaultWeixinClient) {
        Validate.notNull(defaultWeixinClient, "DefaultWeixinClient is required");

        RetryWeiXinClient retryWeiXinApiClient = new RetryWeiXinClient(defaultWeixinClient);

        return retryWeiXinApiClient;
    }

    /**
     * 创建AccessTokenService
     *
     * @param defaultWeixinClient 默认的WeiXinApiClient
     * @return AccessTokenManager
     */
    public static AccessTokenManager buildAccessTokenService(DefaultWeixinClient defaultWeixinClient) {
        Validate.notNull(defaultWeixinClient, "DefaultWeixinClient is required");

        MemoryAccessTokenManager defaultAccessTokenService = new MemoryAccessTokenManager();
        defaultAccessTokenService.setWeixinClient(defaultWeixinClient);
        return defaultAccessTokenService;
    }

    /**
     * 创建 AccessTokenManager
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return AccessTokenManager
     */
    public static AccessTokenManager buildAccessTokenService(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        return WeiXinClients.buildAccessTokenService(new DefaultWeixinClient(appId, appSecret));
    }


}
