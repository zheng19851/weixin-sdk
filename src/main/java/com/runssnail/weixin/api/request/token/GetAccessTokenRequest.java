package com.runssnail.weixin.api.request.token;

import com.runssnail.weixin.api.internal.annotations.AppIdWired;
import com.runssnail.weixin.api.internal.annotations.AppSecretWired;
import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.token.GetAccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信api接口调用凭证请求
 *
 * @author zhengwei
 */
@AppIdWired
@AppSecretWired
public class GetAccessTokenRequest extends GetRequest<GetAccessTokenResponse> {

    /**
     *
     */
    private static final long serialVersionUID = 8446816964474020056L;

    private static final String API_URL = "https://api.weixin.qq.com/cgi-bin/token";


    public GetAccessTokenRequest() {
    }

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");

        return params;
    }

    @Override
    public Class<GetAccessTokenResponse> getResponseClass() {
        return GetAccessTokenResponse.class;
    }

}
