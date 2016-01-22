package com.runssnail.weixin.api.common;

/**
 * 数据格式类型
 * 
 * @author zhengwei
 */
public enum DataType {
    XML, JSON;

    public boolean isJson() {
        return JSON == this;
    }

    public boolean isXml() {
        return XML == this;
    }

}
