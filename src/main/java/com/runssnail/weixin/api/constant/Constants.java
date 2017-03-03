package com.runssnail.weixin.api.constant;

/**
 * 常量
 *
 * @author zhengwei
 */
public abstract class Constants {

    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 连接超时时间，单位毫秒，默认3秒
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 3000;

    /**
     * 读取超时时间，单位毫秒，默认10秒
     */
    public static final int DEFAULT_READ_TIMEOUT = 10000;

    /**
     * http head accept
     */
    public static final String DEFAULT_ACCEPT = "text/xml,text/javascript,text/html";

    /**
     * http head user-agent
     */
    public static final String DEFAULT_USER_AGENT = "runssnail-weixin-sdk-java";

}
