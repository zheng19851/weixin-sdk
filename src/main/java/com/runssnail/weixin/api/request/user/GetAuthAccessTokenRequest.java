package com.runssnail.weixin.api.request.user;

import com.runssnail.weixin.api.AppIdAware;
import com.runssnail.weixin.api.AppSecretAware;
import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.user.GetAuthAccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取网页授权用的access token
 *
 * @author zhengwei
 */
public class GetAuthAccessTokenRequest extends GetRequest<GetAuthAccessTokenResponse> implements AppIdAware, AppSecretAware {

    /**
     *
     */
    private static final long   serialVersionUID = 3418958659448524389L;

    private static final String API_URL          = "https://api.weixin.qq.com/sns/oauth2/access_token";

//    private String              appId;
//    private String              secret;
    private String              code;

    public GetAuthAccessTokenRequest(String code) {
//        this.appId = appId;
//        this.secret = appSecret;
        this.code = code;
    }

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Class<GetAuthAccessTokenResponse> getResponseClass() {
        return GetAuthAccessTokenResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
//        params.put("appid", this.appId);
//        params.put("secret", this.secret);
        params.put("code", this.code);
        params.put("grant_type", "authorization_code");
        return params;
    }

}
