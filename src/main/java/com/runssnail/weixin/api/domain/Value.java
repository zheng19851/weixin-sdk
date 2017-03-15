package com.runssnail.weixin.api.domain;

/**
 * Created by zhengwei on 2017/3/15.
 */
public interface Value {

    String getKey();

    String getStringValue();

    void setOldValue(String value);
}
