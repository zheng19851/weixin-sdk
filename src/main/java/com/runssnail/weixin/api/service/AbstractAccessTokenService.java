package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.credential.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.credential.GetAccessTokenResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhengwei on 2016/3/18.
 */
public abstract class AbstractAccessTokenService implements AccessTokenService {

    protected final Log log = LogFactory.getLog(getClass());

    private WeixinClient weiXinClient;

    @Override
    public String refresh() {
        if (log.isInfoEnabled()) {
            log.info("refreshAccessToken start");
        }

        GetAccessTokenResponse response = weiXinClient.execute(new GetAccessTokenRequest(this.weiXinClient.getAppId(), this.weiXinClient.getAppSecret()));
        if (response.isSuccess()) {
            saveAccessToken(response.getAccess_token());
            if (log.isInfoEnabled()) {
                log.info("refreshAccessToken success, new Access Token->" + response.getAccess_token());
            }

            return response.getAccess_token();
        } else {
            log.error("refreshAccessToken error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());
        }

        return null;
    }

    /**
     * 保存accessToken，由自子类自己实现
     *
     * @param accessToken
     */
    protected abstract void saveAccessToken(String accessToken);

    public void setWeiXinClient(WeixinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public WeixinClient getWeiXinClient() {
        return weiXinClient;
    }
}
