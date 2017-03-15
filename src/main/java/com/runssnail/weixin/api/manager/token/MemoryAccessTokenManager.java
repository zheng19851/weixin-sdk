package com.runssnail.weixin.api.manager.token;

import com.runssnail.weixin.api.domain.token.TokenDO;

/**
 * 用本地内存来保存AccessToken
 * <p>
 * 单机可用，集群时请不要使用
 * <p>
 * Created by zhengwei on 2016/3/18.
 */
public class MemoryAccessTokenManager extends AbstractAccessTokenManager {

    private volatile TokenDO token;

    protected void storeValue(TokenDO valueDO) {
        this.token = valueDO;
    }

    @Override
    public TokenDO getValue() {
        return this.token;
    }

}
