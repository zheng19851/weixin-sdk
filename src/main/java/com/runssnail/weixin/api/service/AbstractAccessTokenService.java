package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.common.Result;
import com.runssnail.weixin.api.domain.token.RefreshTokenDO;
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

        GetAccessTokenResponse response = weiXinClient.execute(new GetAccessTokenRequest());
        if (response.isSuccess()) {
            String old = saveAccessToken(response.getAccessToken());
            if (log.isInfoEnabled()) {
                log.info("refreshAccessToken success, new Access Token->" + response.getAccessToken() + ", old->" + old);
            }

            return response.getAccessToken();
        } else {
            log.error("refreshAccessToken error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());
        }

        return null;
    }

    @Override
    public Result<RefreshTokenDO> refreshAndGet() {
        if (log.isInfoEnabled()) {
            log.info("refreshAndGet start");
        }

        Result<RefreshTokenDO> result = new Result<>();

        GetAccessTokenResponse response = weiXinClient.execute(new GetAccessTokenRequest());
        if (response.isSuccess()) {

            RefreshTokenDO refreshToken = new RefreshTokenDO();

            String old = saveAccessToken(response.getAccessToken());
            if (log.isInfoEnabled()) {
                log.info("refreshAndGet success, new Access Token->" + response.getAccessToken() + ", old->" + old);
            }

            refreshToken.setAccessToken(response.getAccessToken());
            refreshToken.setOldAccessToken(old);
            return result.setSuccess(true).setResult(refreshToken);

        } else {
            log.error("refreshAndGet error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());

            return result.setError(response.getErrcode(), response.getErrmsg());
        }

    }


    /**
     * 保存accessToken，由自子类自己实现
     *
     * @param accessToken
     * @return 老的accessToken
     */
    protected abstract String saveAccessToken(String accessToken);

    public void setWeiXinClient(WeixinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public WeixinClient getWeiXinClient() {
        return weiXinClient;
    }
}
