package com.runssnail.weixin.api.support;

import com.runssnail.weixin.api.AutoRetryWeiXinClient;
import com.runssnail.weixin.api.DefaultWeiXinClient;
import com.runssnail.weixin.api.service.AccessTokenService;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
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
    public static AutoRetryWeiXinClient buildRetryWeiXinClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        DefaultWeiXinClient weiXinClient = buildDefaultWeiXinClient(appId, appSecret);

        return buildRetryWeiXinClient(weiXinClient);
    }

    /**
     * 创建默认的WeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return DefaultWeiXinApiClient
     */
    public static DefaultWeiXinClient buildDefaultWeiXinClient(String appId, String appSecret) {
        Validate.isTrue(StringUtils.isNotBlank(appId), "appId is required");
        Validate.isTrue(StringUtils.isNotBlank(appSecret), "appSecret is required");

        DefaultWeiXinClient defaultWeixinClient = new DefaultWeiXinClient(appId, appSecret);

        return defaultWeixinClient;
    }

    /**
     * 创建RetryWeiXinApiClient
     *
     * @param defaultWeixinClient 默认的WeiXinApiClient
     * @return RetryWeiXinApiClient
     */
    public static AutoRetryWeiXinClient buildRetryWeiXinClient(DefaultWeiXinClient defaultWeixinClient) {
        Validate.notNull(defaultWeixinClient, "DefaultWeiXinClient is required");

        AutoRetryWeiXinClient retryWeiXinApiClient = new AutoRetryWeiXinClient(defaultWeixinClient);

        return retryWeiXinApiClient;
    }

    /**
     * 创建AccessTokenService
     *
     * @param defaultWeixinClient 默认的WeiXinApiClient
     * @return AccessTokenService
     */
    public static AccessTokenService buildAccessTokenService(DefaultWeiXinClient defaultWeixinClient) {
        Validate.notNull(defaultWeixinClient, "DefaultWeiXinClient is required");

        MemoryAccessTokenService defaultAccessTokenService = new MemoryAccessTokenService();
        defaultAccessTokenService.setWeiXinClient(defaultWeixinClient);
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

        return WeiXinClients.buildAccessTokenService(new DefaultWeiXinClient(appId, appSecret));
    }


}
