package com.runssnail.weixin.api.support;

/**
 * 自动注入app ID
 *
 * 你妹的,微信企业付款接口里的app id参数名称还和其他的不一致
 *
 * Created by zhengwei on 16/6/1.
 */
public interface AppIdKeyAware {

    String getAppIdKey();
}
