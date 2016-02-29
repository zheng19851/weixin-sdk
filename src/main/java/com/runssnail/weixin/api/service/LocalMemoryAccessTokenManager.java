package com.runssnail.weixin.api.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.runssnail.weixin.api.DefaultWeiXinClient;
import com.runssnail.weixin.api.common.Result;

/**
 * accessToken提供本地内存缓存功能，并定时刷新
 *
 * @author zhengwei
 */
public class LocalMemoryAccessTokenManager extends AbstractAccessTokenManager {

    /**
     * 主动调用微信平台接口时需要用到
     */
    private volatile String          accessTokenCache;

    /**
     * 定时刷新accessToken用
     */
    private ScheduledExecutorService executor;

    /**
     * 刷新时段，默认没5400秒(一个半小时)刷新一次
     */
    private int                      refreshPeriod = 5400;

    /**
     * 是否禁止缓存
     */
    private volatile boolean         disableCache  = false;

    public LocalMemoryAccessTokenManager(DefaultWeiXinClient weixinApiClient) {
        super(weixinApiClient);
    }

    @Override
    public String getAccessToken() {
        if (this.disableCache) {
            return this.accessTokenService.getAccessToken();
        }
        return this.accessTokenCache;
    }

    @Override
    public void doInit() {

        // 初始化
        refresh();

        if (!disableCache) {
            if (executor == null) {
                executor = Executors.newSingleThreadScheduledExecutor();
            }

            executor.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    try {
                        refresh();
                    } catch (Exception e) {
                        log.error("refresh access token error", e);
                    }
                }

            }, this.refreshPeriod, this.refreshPeriod, TimeUnit.SECONDS);
        }

    }

    @Override
    public void doClose() {
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }

    @Override
    public Result<String> refresh() {
        Result<String> result = new Result<String>();

        String oldAccessToken = this.accessTokenCache;

        // 实际上的accessToken服务
        String accessToken = accessTokenService.getAccessToken();
        this.accessTokenCache = accessToken;

        if (log.isDebugEnabled()) {
            log.debug("refresh accessToken success, oldAccessToken=" + oldAccessToken + ", newAccessToken="
                    + accessToken);
        }

        result.setSuccess(true).setResult(accessToken);

        return result;
    }

    public int getRefreshPeriod() {
        return refreshPeriod;
    }

    public void setRefreshPeriod(int refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    public boolean isDisableCache() {
        return disableCache;
    }

    public void setDisableCache(boolean disableCache) {
        this.disableCache = disableCache;
    }

    public ScheduledExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }

}
