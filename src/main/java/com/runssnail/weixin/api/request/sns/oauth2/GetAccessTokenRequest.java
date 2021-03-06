package com.runssnail.weixin.api.request.sns.oauth2;

import com.runssnail.weixin.api.exception.ApiRuleException;
import com.runssnail.weixin.api.internal.annotations.AppIdWired;
import com.runssnail.weixin.api.internal.annotations.AppSecretWired;
import com.runssnail.weixin.api.internal.support.ApiRuleValidate;
import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.sns.oauth2.GetAccessTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取网页授权用的access token
 * <p>
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842&token=&lang=zh_CN
 *
 * @author zhengwei
 */
@AppIdWired
@AppSecretWired
public class GetAccessTokenRequest extends GetRequest<GetAccessTokenResponse> {

    /**
     *
     */
    private static final long serialVersionUID = 3418958659448524389L;

    private static final String API_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    private String code;

    /**
     * 获取访问令牌请求
     *
     * @param code 授权code
     */
    public GetAccessTokenRequest(String code) {
        this.code = code;
    }

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Class<GetAccessTokenResponse> getResponseClass() {
        return GetAccessTokenResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("code", this.code);
        params.put("grant_type", "authorization_code");
        return params;
    }

    @Override
    protected void doCheck() throws ApiRuleException {
        ApiRuleValidate.notEmpty(this.code, "code is required");
    }
}
