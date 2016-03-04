package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.DefaultWeiXinClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * {@link AccessTokenManager} 抽象实现
 *
 * @author zhengwei
 */
public abstract class AbstractAccessTokenManager implements AccessTokenManager {

    protected final Log log = LogFactory.getLog(getClass());

    protected AccessTokenService accessTokenService;

    /**
     * 是否已经初始化
     */
    private volatile boolean     initialized = false;

    public AbstractAccessTokenManager(DefaultWeiXinClient weixinApiClient) {
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
