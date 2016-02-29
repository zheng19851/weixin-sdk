package com.runssnail.weixin.api.support;

import com.runssnail.weixin.api.DefaultWeiXinClient;
import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.service.AccessTokenManager;
import com.runssnail.weixin.api.service.AccessTokenService;
import com.runssnail.weixin.api.service.DefaultAccessTokenService;
import com.runssnail.weixin.api.service.LocalMemoryAccessTokenManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * WeiXinApiClient创建帮助类
 *
 * @author zhengwei
 * @since 1.0-SNAPSHOT
 */
public class WeixinApiClients {

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return RetryWeiXinApiClient
     */
    public static RetryWeiXinClient buildRetryWeixinClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        return WeixinApiClients.buildRetryWeixinClient(appId, appSecret, null);
    }

    /**
     * 创建默认的WeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return DefaultWeiXinApiClient
     */
    public static DefaultWeiXinClient buildDefaultWeixinClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        DefaultWeiXinClient defaultWeixinClient = new DefaultWeiXinClient(appId, appSecret);
        defaultWeixinClient.init();

        return defaultWeixinClient;
    }

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param defaultWeixinClient 默认的WeiXinApiClient
     * @param accessTokenManager     AccessTokenManager
     * @return RetryWeiXinApiClient
     */
    public static RetryWeiXinClient buildRetryWeixinClient(DefaultWeiXinClient defaultWeixinClient,
                                                           AccessTokenManager accessTokenManager) {
        Validate.notNull(defaultWeixinClient, "DefaultWeixinClient is required");

        if (accessTokenManager == null) {
            accessTokenManager = buildAccessTokenManager(defaultWeixinClient);
        }

        RetryWeiXinClient retryWeiXinApiClient = new RetryWeiXinClient(defaultWeixinClient);

        retryWeiXinApiClient.setAccessTokenManager(accessTokenManager);

        retryWeiXinApiClient.init();

        return retryWeiXinApiClient;
    }

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param accessTokenManager AccessTokenManager
     * @return RetryWeiXinApiClient
     */
    public static RetryWeiXinClient buildRetryWeixinClient(String appId, String appSecret,
                                                           AccessTokenManager accessTokenManager) {

        DefaultWeiXinClient defaultWeixinClient = new DefaultWeiXinClient(appId, appSecret);

        // AccessTokenManager
        if (accessTokenManager == null) {
            accessTokenManager = buildAccessTokenManager(defaultWeixinClient);
        }

        RetryWeiXinClient retryWeiXinApiClient = new RetryWeiXinClient(defaultWeixinClient);

        retryWeiXinApiClient.setAccessTokenManager(accessTokenManager);

        retryWeiXinApiClient.init();

        return retryWeiXinApiClient;
    }

    /**
     * 创建AccessTokenService
     *
     * @param defaultWeixinClient 默认的WeiXinApiClient
     * @return AccessTokenService
     */
    public static AccessTokenService buildAccessTokenService(DefaultWeiXinClient defaultWeixinClient) {
        Validate.notNull(defaultWeixinClient, "DefaultWeixinClient is required");

        DefaultAccessTokenService defaultAccessTokenService = new DefaultAccessTokenService();
        defaultAccessTokenService.setWeiXinClient(defaultWeixinClient);
        defaultAccessTokenService.init();
        return defaultAccessTokenService;
    }

    /**
     * 创建 AccessTokenService
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return AccessTokenService
     */
    public static AccessTokenService buildAccessTokenService(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        return WeixinApiClients.buildAccessTokenService(new DefaultWeiXinClient(appId, appSecret));
    }

    /**
     * 创建AccessTokenManager
     *
     * @param defaultWeixinClient DefaultWeiXinApiClient
     * @return AccessTokenManager
     */
    public static AccessTokenManager buildAccessTokenManager(DefaultWeiXinClient defaultWeixinClient) {
        Validate.notNull(defaultWeixinClient, "DefaultWeixinClient is required");

        LocalMemoryAccessTokenManager accessTokenManager = new LocalMemoryAccessTokenManager(defaultWeixinClient);
        accessTokenManager.init();
        return accessTokenManager;
    }

}
