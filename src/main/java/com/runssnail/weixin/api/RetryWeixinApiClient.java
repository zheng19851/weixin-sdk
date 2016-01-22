package com.runssnail.weixin.api;

import org.apache.log4j.Logger;

import com.runssnail.weixin.api.common.Result;
import com.runssnail.weixin.api.config.WeixinConfig;
import com.runssnail.weixin.api.exception.WeixinApiException;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.service.AccessTokenManager;
import com.runssnail.weixin.api.internal.support.WeixinApiHelper;

/**
 * 提供重试功能，比如 accessToken失效时，会主动刷新，并重新执行
 *
 * @author zhengwei
 */
public class RetryWeixinApiClient implements WeixinApiClient {

    private final Logger       log           = Logger.getLogger(getClass());

    /**
     * 实际的WeiXinApiClient服务
     */
    private WeixinApiClient delegate;

    /**
     * 默认重试一次
     */
    private int                retryCount    = 1;

    /**
     * 重试等待间隔，默认300毫秒
     */
    private long               retryWait     = 300;

    /**
     * 重试模板
     */
    private RetryTemplate      retryTemplate = new RetryTemplate();

    /**
     * access token 管理器
     */
    private AccessTokenManager accessTokenManager;

    /**
     * 是否已经初始化
     */
    private volatile boolean   initialized   = false;

    public RetryWeixinApiClient(WeixinApiClient delegate) {
        this.delegate = delegate;
    }

    public AccessTokenManager getAccessTokenManager() {
        return accessTokenManager;
    }

    public void setAccessTokenManager(AccessTokenManager accessTokenManager) {
        this.accessTokenManager = accessTokenManager;
    }

    public Result<String> refreshAccessToken() {
        return this.accessTokenManager.refresh();
    }

    public String getAccessToken() {
        return this.accessTokenManager.getAccessToken();
    }

    @Override
    public String getAppId() {
        return delegate.getAppId();
    }

    @Override
    public String getAppSecret() {
        return delegate.getAppSecret();
    }

    /**
     * 重试模板
     *
     * @author zhengwei
     */
    private class RetryTemplate {

        public <R extends Response> R execute(String accessToken, Callback<R> callback) {
            R res = null;

            // 一次是肯定要执行的
            for (int i = 0; i <= retryCount; i++) {

                if (i > 0) {
                    log.warn("retry execute api (" + i + "), previous result=" + res);

                    if (retryWait > 0) {
                        // 等待一会再重试，间隔太短，没什么效果
                        try {
                            Thread.sleep(retryWait);
                        } catch (InterruptedException e) {
                            log.warn("retry wait is interrupted,", e);
                        }
                    }
                }

                res = callback.doAction(accessToken);
                if (res.isSuccess()) {
                    return res;
                }

                if (isAccessTokenInvalid(res) && accessTokenManager != null) {
                    if (log.isInfoEnabled()) {
                        log.info("access token is invalid, so refresh.");
                    }

                    // 刷新access token
                    Result<String> result = accessTokenManager.refresh();
                    if (!result.isSuccess()) {
                        return res;
                    }

                    accessToken = result.getResult();
                } else {
                    return res;
                }

            }

            return res;
        }

    }

    interface Callback<R extends Response> {

        R doAction(String accessToken);
    }

    /**
     * 可重试原因，出错了&&是access_token过期
     *
     * @param res 响应对象
     * @return access token 是否有效
     */
    private boolean isAccessTokenInvalid(Response res) {
        return WeixinApiHelper.isAccessTokenInvalid(res);
    }

    public WeixinApiClient getDelegate() {
        return delegate;
    }

    public void setDelegate(WeixinApiClient delegate) {
        this.delegate = delegate;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryWait() {
        return retryWait;
    }

    public void setRetryWait(long retryWait) {
        this.retryWait = retryWait;
    }

    @Override
    public void init() {

        if (this.initialized) {
            return;
        }

        this.delegate.init();

        this.initialized = true;
    }

    @Override
    public void close() {
        this.delegate.close();
        if (this.accessTokenManager != null) {
            this.accessTokenManager.close();
        }
    }

    @Override
    public <R extends Response> R execute(final Request<R> req) throws WeixinApiException {

        return retryTemplate.execute(null, new Callback<R>() {

            @Override
            public R doAction(String accessToken) {
                return delegate.execute(req);
            }

        });
    }

    @Override
    public <R extends Response> R execute(final Request<R> req, final String accessToken) throws WeixinApiException {
        return retryTemplate.execute(accessToken, new Callback<R>() {

            @Override
            public R doAction(String accessToken) {
                return delegate.execute(req, accessToken);
            }

        });
    }


    /**
     * 自动添加access_token
     *
     * @param req 请求
     * @param isAccessTokenNecessary true会自动添加access_token
     * @return
     * @throws WeixinApiException
     */
    public <R extends Response> R execute(final Request<R> req, boolean isAccessTokenNecessary)
            throws WeixinApiException {

        if (isAccessTokenNecessary) {

            return retryTemplate.execute(getAccessToken(), new Callback<R>() {

                @Override
                public R doAction(String accessToken) {
                    return delegate.execute(req, accessToken);
                }

            });
        } else {
            return retryTemplate.execute(null, new Callback<R>() {

                @Override
                public R doAction(String accessToken) {
                    return delegate.execute(req);
                }

            });
        }

    }

}
