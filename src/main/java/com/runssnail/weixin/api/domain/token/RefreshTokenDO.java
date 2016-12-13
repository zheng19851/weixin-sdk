package com.runssnail.weixin.api.domain.token;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * Created by zhengwei on 2016/12/13.
 */
public class RefreshTokenDO extends BaseDomain {

    /**
     * 新的accessToken
     */
    private String accessToken;

    /**
     * 老的accessToken
     */
    private String oldAccessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOldAccessToken() {
        return oldAccessToken;
    }

    public void setOldAccessToken(String oldAccessToken) {
        this.oldAccessToken = oldAccessToken;
    }
}
