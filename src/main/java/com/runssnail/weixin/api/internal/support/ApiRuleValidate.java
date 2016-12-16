package com.runssnail.weixin.api.internal.support;

import com.runssnail.weixin.api.exception.ApiRuleException;

import java.util.Collection;
import java.util.Map;

/**
 * ApiRuleValidate
 *
 * Created by zhengwei on 2015/7/22.
 */
public abstract class ApiRuleValidate {

    public static void isTrue(boolean expression, String message) {
        if (expression == false) {
            throw new ApiRuleException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new ApiRuleException(message);
        }
    }

    public static void notEmpty(String object, String message) {
        if (object == null || object.length() == 0) {
            throw new ApiRuleException(message);
        }
    }

    public static void notEmpty(Collection collection) {
        notEmpty(collection, "The validated collection is empty");
    }

    public static void notEmpty(Collection collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new ApiRuleException(message);
        }
    }

    public static void notEmpty(Map map, String message) {
        if (map == null || map.size() == 0) {
            throw new ApiRuleException(message);
        }
    }
}
