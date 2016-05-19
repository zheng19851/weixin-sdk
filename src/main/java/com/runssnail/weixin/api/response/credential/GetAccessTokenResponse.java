package com.runssnail.weixin.api.response.credential;

import com.alibaba.fastjson.annotation.JSONField;
import com.runssnail.weixin.api.response.JSONResponse;

/**
 * 微信api接口调用凭证响应对象
 *
 * @author zhengwei
 */
public class GetAccessTokenResponse extends JSONResponse {

    /**
     *
     */
    private static final long serialVersionUID = 4521140586733813964L;

    /**
     * 获取到的凭证
     */
    @JSONField(name = "access_token")
    private String            accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    @JSONField(name = "expires_in")
    private long              expiresIn;

    public String getAccess_token() {
        return accessToken;
    }

    public long getExpires_in() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
