package com.runssnail.weixin.api.internal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动添加appid到请求参数里
 *
 * Created by zhengwei on 2016/11/24.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppIdWired {

    String value() default "appid";
}
