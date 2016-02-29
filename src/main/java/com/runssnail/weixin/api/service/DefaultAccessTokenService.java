package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeiXinClient;
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

    private WeiXinClient weiXinClient;

    /**
     * 是否已经初始化
     */
    private volatile boolean initialized = false;

    public DefaultAccessTokenService() {
    }

    public DefaultAccessTokenService(WeiXinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public synchronized void init() {
        if (this.initialized) {
            return;
        }

        this.weiXinClient.init();

        this.initialized = true;
    }

    private Result<String> refresh() {
        Result<String> result = new Result<String>();

        GetAccessTokenRequest req = new GetAccessTokenRequest(weiXinClient.getAppId(),
                weiXinClient.getAppSecret());

        GetAccessTokenResponse res = weiXinClient.execute(req);

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
        this.weiXinClient.close();
    }

    public WeiXinClient getWeiXinClient() {
        return weiXinClient;
    }

    public void setWeiXinClient(WeiXinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

}
