package com.runssnail.weixin.api.manager.token;


import com.runssnail.weixin.api.domain.token.TokenDO;
import com.runssnail.weixin.api.manager.RedisClient;

/**
 * 用redis来存放AccessToken
 * <p>
 * Created by zhengwei on 2016/3/17.
 */
public class RedisAccessTokenManager extends AbstractAccessTokenManager {

    /**
     * 保存在redis里key的前缀, prefix + appid
     */
    private static final String ACCESS_TOKEN_KEY_PREFIX = "weixin:token:";

    private RedisClient redisClient;

    /**
     * 保存在redis里的token前缀
     */
    private String tokenPrefix = ACCESS_TOKEN_KEY_PREFIX;

    /**
     * 存活时间，单位秒，默认7000秒
     */
    private long liveTime = 7000;

    @Override
    public TokenDO getValue() {
        return (TokenDO) redisClient.get(getKey());
    }

    protected String getKey() {
        return this.tokenPrefix + getAppId();
    }

    @Override
    protected void storeValue(TokenDO valueDO) {
        this.redisClient.set(getKey(), valueDO, this.liveTime);
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public long getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(long liveTime) {
        this.liveTime = liveTime;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }


}
