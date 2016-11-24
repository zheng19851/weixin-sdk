package com.runssnail.weixin.api.internal.annotations;

/**
 * 自动添加appid到请求参数里
 *
 * Created by zhengwei on 2016/11/24.
 */
public @interface AppIdWired {

    String value() default "appid";
}
