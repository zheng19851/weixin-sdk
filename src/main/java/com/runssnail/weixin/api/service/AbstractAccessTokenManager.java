package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.DefaultWeixinApiClient;
import org.apache.log4j.Logger;

/**
 * {@link AccessTokenManager} 抽象实现
 *
 * @author zhengwei
 */
public abstract class AbstractAccessTokenManager implements AccessTokenManager {

    protected final Logger log         = Logger.getLogger(getClass());

    protected AccessTokenService accessTokenService;

    /**
     * 是否已经初始化
     */
    private volatile boolean     initialized = false;

    public AbstractAccessTokenManager(DefaultWeixinApiClient weixinApiClient) {
        this.accessTokenService = new DefaultAccessTokenService(weixinApiClient);
    }

    @Override
    public synchronized final void init() {
        if (this.initialized) {
            return;
        }

        this.accessTokenService.init();

        doInit();

        this.initialized = true;

    }

    protected void doInit() {

    }

    @Override
    public final void close() {
        doClose();
        this.accessTokenService.close();
    }

    protected void doClose() {

    }

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

}
