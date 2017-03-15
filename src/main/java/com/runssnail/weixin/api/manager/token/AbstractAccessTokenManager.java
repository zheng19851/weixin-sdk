package com.runssnail.weixin.api.manager.token;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.domain.token.TokenDO;
import com.runssnail.weixin.api.manager.AbstractValueManager;
import com.runssnail.weixin.api.request.token.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.token.GetAccessTokenResponse;
import com.runssnail.weixin.api.result.Result;

/**
 * Created by zhengwei on 2016/3/18.
 */
public abstract class AbstractAccessTokenManager extends AbstractValueManager<TokenDO> implements AccessTokenManager {

    private WeixinClient weixinClient;

    public String getAppId() {
        return this.weixinClient.getAppId();
    }

    @Override
    public String getAccessToken() {

        return this.getStringValue();
    }

    @Override
    protected Result<TokenDO> doRefresh() {
        if (log.isInfoEnabled()) {
            log.info("refreshAndGet start");
        }

        Result<TokenDO> result = new Result<>();

        GetAccessTokenResponse response = weixinClient.execute(new GetAccessTokenRequest());
        if (response.isSuccess()) {

            if (log.isInfoEnabled()) {
                log.info("refreshAndGet success, new Access Token->" + response.getAccessToken() + ", response->" + response);
            }

            TokenDO value = new TokenDO(getAppId(), response.getAccessToken(), System.currentTimeMillis());

            return result.setSuccess(true).setResult(value);

        } else {
            log.error("refreshAndGet error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());

            return result.setError(response.getErrcode(), response.getErrmsg());
        }
    }

    public WeixinClient getWeixinClient() {
        return weixinClient;
    }

    public void setWeixinClient(WeixinClient weixinClient) {
        this.weixinClient = weixinClient;
    }
}
