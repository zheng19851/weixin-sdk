package com.runssnail.weixin.api.request.web;

import com.runssnail.weixin.api.internal.annotations.AppIdWired;
import com.runssnail.weixin.api.internal.annotations.AppSecretWired;
import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.web.GetAuthAccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取网页授权用的access token
 *
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842&token=&lang=zh_CN
 *
 * @author zhengwei
 * @see com.runssnail.weixin.api.request.sns.oauth2.GetAccessTokenRequest
 */
@AppIdWired
@AppSecretWired
@Deprecated
public class GetAuthAccessTokenRequest extends GetRequest<GetAuthAccessTokenResponse> {

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
