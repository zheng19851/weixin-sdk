package com.runssnail.weixin.api.internal.support;

/**
 * 自动注入app ID
 *
 * 你妹的,微信企业付款接口里的app id参数名称还和其他的不一致
 *
 * @see com.runssnail.weixin.api.internal.annotations.AppIdWired
 *
 * Created by zhengwei on 16/6/1.
 */
@Deprecated
public interface AppIdKeyAware {

    String getAppIdKey();
}
