package com.runssnail.weixin.api.internal.support;

import com.runssnail.weixin.api.exception.WeixinApiRuleException;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * WeiXinApiRuleValidate
 *
 * Created by zhengwei on 2015/7/22.
 */
public abstract class WeixinApiRuleValidate {

    public static void isTrue(boolean expression, String message) {
        if (expression == false) {
            throw new WeixinApiRuleException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new WeixinApiRuleException(message);
        }
    }

    public static void notBlank(String object, String message) {
        if (StringUtils.isBlank(object)) {
            throw new WeixinApiRuleException(message);
        }
    }

    public static void notEmpty(Collection collection) {
        notEmpty(collection, "The validated collection is empty");
    }

    public static void notEmpty(Collection collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new WeixinApiRuleException(message);
        }
    }
}
