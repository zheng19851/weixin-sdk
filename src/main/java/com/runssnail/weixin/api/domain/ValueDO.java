package com.runssnail.weixin.api.domain;

/**
 * Created by zhengwei on 2016/12/13.
 */
public class ValueDO extends BaseDomain implements Value {

    private static final long serialVersionUID = 9167792665446623374L;

    private String key;

    /**
     * 新的value
     */
    private String value;

    /**
     * 老的value
     */
    private String oldValue;

    /**
     * 更新时间
     */
    private long lastUpdateTime;

    public ValueDO(String key, String value, long lastUpdateTime) {
        this.key = key;
        this.value = value;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getStringValue() {
        return this.value;
    }
}
