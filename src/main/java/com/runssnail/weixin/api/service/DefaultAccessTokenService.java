package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinApiClient;
import com.runssnail.weixin.api.common.Result;
import com.runssnail.weixin.api.request.credential.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.credential.GetAccessTokenResponse;
import org.apache.log4j.Logger;

/**
 * 默认的获取accessToken服务
 *
 * @author zhengwei
 */
public class DefaultAccessTokenService implements AccessTokenService {

    private final Logger log         = Logger.getLogger(getClass());

    private WeixinApiClient weixinApiClient;

    /**
     * 是否已经初始化
     */
    private volatile boolean initialized = false;

    public DefaultAccessTokenService() {
    }

    public DefaultAccessTokenService(WeixinApiClient weixinApiClient) {
        this.weixinApiClient = weixinApiClient;
    }

    public synchronized void init() {
        if (this.initialized) {
            return;
        }

        this.weixinApiClient.init();

        this.initialized = true;
    }

    private Result<String> refresh() {
        Result<String> result = new Result<String>();

        GetAccessTokenRequest req = new GetAccessTokenRequest(weixinApiClient.getAppId(),
                weixinApiClient.getAppSecret());

        GetAccessTokenResponse res = weixinApiClient.execute(req);

        if (res.isSuccess()) {
            result.setResult(res.getAccess_token()).setSuccess(true);
        } else {
            result.setError(res.getErrcode(), res.getErrmsg());
        }

        return result;
    }

    @Override
    public String getAccessToken() {
        Result<String> result = refresh();

        return result.getResult();
    }

    @Override
    public void close() {
        this.weixinApiClient.close();
    }

    public WeixinApiClient getWeixinApiClient() {
        return weixinApiClient;
    }

    public void setWeixinApiClient(WeixinApiClient weixinApiClient) {
        this.weixinApiClient = weixinApiClient;
    }

}
