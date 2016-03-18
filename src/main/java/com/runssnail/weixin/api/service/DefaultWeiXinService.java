package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeiXinClient;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 默认的微信服务实现
 *
 * Created by zhengwei on 2016/3/17.
 */
public class DefaultWeiXinService implements WeiXinService {

    private static final Log log = LogFactory.getLog(DefaultWeiXinService.class);

    private WeiXinClient weiXinClient;

    private AccessTokenService accessTokenService;

    @Override
    public String getAppId() {
        return weiXinClient.getAppId();
    }

    @Override
    public String getAppSecret() {
        return weiXinClient.getAppSecret();
    }

    @Override
    public void refreshAccessToken() {

        if (log.isInfoEnabled()) {
            log.info("refreshAccessToken start");
        }

        String accessToken = accessTokenService.refresh();

        if (log.isInfoEnabled()) {
            log.info("refreshAccessToken end, new Access Token->" + accessToken);
        }

    }

    @Override
    public <R extends Response> R execute(Request<R> request) {
        String accessToken = this.accessTokenService.getAccessToken();
        return weiXinClient.execute(request, accessToken);
    }

}
