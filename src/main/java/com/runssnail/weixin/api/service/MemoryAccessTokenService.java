package com.runssnail.weixin.api.service;

import org.apache.commons.lang.StringUtils;

/**
 * 用本地内存来保存AccessToken
 *
 * 单机可用，集群时请不要使用
 *
 * Created by zhengwei on 2016/3/18.
 */
public class MemoryAccessTokenService extends AbstractAccessTokenService {

    /**
     * AccessToken
     */
    private volatile String accessToken;

    @Override
    public String getAccessToken() {

        if (StringUtils.isBlank(this.accessToken)) {
            return refresh();
        }

        return this.accessToken;
    }

    @Override
    protected void saveAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
