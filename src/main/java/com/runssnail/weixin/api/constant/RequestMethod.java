package com.runssnail.weixin.api.constant;

/**
 * http请求方式
 * 
 * @author zhengwei
 *
 */
public enum RequestMethod {

    GET, POST;

    public boolean isGet() {
        return GET == this;
    }

    public boolean isPost() {
        return POST == this;
    }
}
