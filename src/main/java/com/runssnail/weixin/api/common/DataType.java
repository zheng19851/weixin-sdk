package com.runssnail.weixin.api.common;

/**
 * 数据格式类型
 * 
 * @author zhengwei
 */
public enum DataType {
    XML, JSON, TXT;

    public boolean isJson() {
        return JSON == this;
    }

    public boolean isXml() {
        return XML == this;
    }

    public boolean isTxt() {return TXT == this;}

}
