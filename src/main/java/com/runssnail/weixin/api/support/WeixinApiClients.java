package com.runssnail.weixin.api.support;

import com.runssnail.weixin.api.DefaultWeixinApiClient;
import com.runssnail.weixin.api.RetryWeixinApiClient;
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
    public static RetryWeixinApiClient buildRetryWeixinApiClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        return WeixinApiClients.buildRetryWeixinApiClient(appId, appSecret, null);
    }

    /**
     * 创建默认的WeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return DefaultWeiXinApiClient
     */
    public static DefaultWeixinApiClient buildDefaultWeixinApiClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        DefaultWeixinApiClient defaultWeiXinApiClient = new DefaultWeixinApiClient(appId, appSecret);
        defaultWeiXinApiClient.init();

        return defaultWeiXinApiClient;
    }

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param defaultWeiXinApiClient 默认的WeiXinApiClient
     * @param accessTokenManager     AccessTokenManager
     * @return RetryWeiXinApiClient
     */
    public static RetryWeixinApiClient buildRetryWeixinApiClient(DefaultWeixinApiClient defaultWeiXinApiClient,
                                                                 AccessTokenManager accessTokenManager) {
        Validate.notNull(defaultWeiXinApiClient, "DefaultWeiXinApiClient is required");

        if (accessTokenManager == null) {
            accessTokenManager = buildAccessTokenManager(defaultWeiXinApiClient);
        }

        RetryWeixinApiClient retryWeiXinApiClient = new RetryWeixinApiClient(defaultWeiXinApiClient);

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
    public static RetryWeixinApiClient buildRetryWeixinApiClient(String appId, String appSecret,
                                                                 AccessTokenManager accessTokenManager) {

        DefaultWeixinApiClient defaultWeiXinApiClient = new DefaultWeixinApiClient(appId, appSecret);

        // AccessTokenManager
        if (accessTokenManager == null) {
            accessTokenManager = buildAccessTokenManager(defaultWeiXinApiClient);
        }

        RetryWeixinApiClient retryWeiXinApiClient = new RetryWeixinApiClient(defaultWeiXinApiClient);

        retryWeiXinApiClient.setAccessTokenManager(accessTokenManager);

        retryWeiXinApiClient.init();

        return retryWeiXinApiClient;
    }

    /**
     * 创建AccessTokenService
     *
     * @param defaultWeiXinApiClient 默认的WeiXinApiClient
     * @return AccessTokenService
     */
    public static AccessTokenService buildAccessTokenService(DefaultWeixinApiClient defaultWeiXinApiClient) {
        Validate.notNull(defaultWeiXinApiClient, "DefaultWeiXinApiClient is required");

        DefaultAccessTokenService defaultAccessTokenService = new DefaultAccessTokenService();
        defaultAccessTokenService.setWeixinApiClient(defaultWeiXinApiClient);
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

        return WeixinApiClients.buildAccessTokenService(new DefaultWeixinApiClient(appId, appSecret));
    }

    /**
     * 创建AccessTokenManager
     *
     * @param defaultWeiXinApiClient DefaultWeiXinApiClient
     * @return AccessTokenManager
     */
    public static AccessTokenManager buildAccessTokenManager(DefaultWeixinApiClient defaultWeiXinApiClient) {
        Validate.notNull(defaultWeiXinApiClient, "DefaultWeiXinApiClient is required");

        LocalMemoryAccessTokenManager accessTokenManager = new LocalMemoryAccessTokenManager(defaultWeiXinApiClient);
        accessTokenManager.init();
        return accessTokenManager;
    }

}
