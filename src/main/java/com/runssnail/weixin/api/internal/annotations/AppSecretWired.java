package com.runssnail.weixin.api.internal.annotations;

/**
 * 自动添加app secret到请求参数里
 *
 * Created by zhengwei on 2016/11/24.
 */
public @interface AppSecretWired {

    String value() default "secret";
}
