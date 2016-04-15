package com.runssnail.weixin.api.service;


import org.apache.commons.lang.StringUtils;

/**
 * 用redis来存放AccessToken
 *
 * Created by zhengwei on 2016/3/17.
 */
public class RedisAccessTokenService extends AbstractAccessTokenService {

    private RedisClient redisClient;

    /**
     * 保存在redis里key的前缀, prefix + appid
     */
    private static final String ACCESS_TOKEN_KEY_PREFIX = "weixin:access_token:";

    @Override
    public String getAccessToken() {

        String accessToken = redisClient.getString(ACCESS_TOKEN_KEY_PREFIX + getAppId());
        if (StringUtils.isBlank(accessToken)) {
            accessToken = refresh();
        }

        return accessToken;
    }

    @Override
    protected void saveAccessToken(String accessToken) {

        // 默认保存7200秒，也就是2个小时
        this.redisClient.set(ACCESS_TOKEN_KEY_PREFIX + getAppId(), accessToken, 7200);
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public String getAppId() {
        return this.getWeiXinClient().getAppId();
    }
}
