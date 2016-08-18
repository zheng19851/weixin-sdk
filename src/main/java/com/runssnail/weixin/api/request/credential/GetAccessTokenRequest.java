package com.runssnail.weixin.api.request.credential;

import com.runssnail.weixin.api.AppIdAware;
import com.runssnail.weixin.api.AppSecretAware;
import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.credential.GetAccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * ΢微信api接口调用凭证请求
 *
 * @author zhengwei
 */
public class GetAccessTokenRequest extends GetRequest<GetAccessTokenResponse> implements AppIdAware, AppSecretAware {

    /**
     *
     */
    private static final long serialVersionUID = 8446816964474020056L;

    private static final String API_URL = "https://api.weixin.qq.com/cgi-bin/token";

//    private String              appId;
//
//    private String              appSecret;

    public GetAccessTokenRequest() {
//        this.appId = appId;
//        this.appSecret = appSecret;
    }

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");
//        params.put("appid", appId);
//        params.put("secret", this.appSecret);

        return params;
    }

    @Override
    public Class<GetAccessTokenResponse> getResponseClass() {
        return GetAccessTokenResponse.class;
    }

}
