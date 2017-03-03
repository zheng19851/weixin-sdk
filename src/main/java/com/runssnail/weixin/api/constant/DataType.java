package com.runssnail.weixin.api.constant;

/**
 * 数据格式类型
 *
 * @author zhengwei
 */
public enum DataType {
    XML, JSON, TXT, BYTE;

    public boolean isJson() {
        return JSON == this;
    }

    public boolean isXml() {
        return XML == this;
    }

    public boolean isTxt() {
        return TXT == this;
    }

    public boolean isByte() {
        return BYTE == this;
    }

}
