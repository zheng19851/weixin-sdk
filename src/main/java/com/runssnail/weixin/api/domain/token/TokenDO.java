package com.runssnail.weixin.api.domain.token;

import com.runssnail.weixin.api.domain.ValueDO;

/**
 * Created by zhengwei on 2017/3/15.
 */
public class TokenDO extends ValueDO {

    public TokenDO(String key, String value, long lastUpdateTime) {
        super(key, value, lastUpdateTime);
    }
}
