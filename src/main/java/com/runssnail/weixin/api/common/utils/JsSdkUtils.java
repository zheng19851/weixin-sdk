package com.runssnail.weixin.api.common.utils;

import com.runssnail.weixin.api.domain.jssdk.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.Validate;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * js-sdk工具类
 *
 * Created by zhengwei on 2016/3/17.
 */
public class JsSdkUtils {

    /**
     * 获取js-sdk用的参数
     *
     * @param appId appid
     * @param ticket 调用微信卡券JS API的临时票据
     * @param url 当前网页的URL
     * @return
     */
    public static Config getConfig(String appId, String ticket, String url) {
        Validate.notNull(appId, "appId is required");
        Validate.notNull(ticket, "ticket is required");
        Validate.notNull(url, "url is required");

        String timestamp = String.valueOf(System.currentTimeMillis() / 1000); // 微信控制了单位为秒

        String nonceStr = SignUtils.buildNonce();

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("noncestr", nonceStr);
        params.put("jsapi_ticket", ticket);
        params.put("timestamp", timestamp);
        params.put("url", url);

        String signature = buildSign(params);

        Config config = new Config(appId, timestamp, nonceStr, signature, ticket);

        return config;
    }

    private static String buildSign(SortedMap<String, String> params) {

        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> es = params.entrySet();
        for (Map.Entry<String, String> entry : es) {
            String k = entry.getKey();
            String v = entry.getValue();

            sb.append(k + "=" + v + "&");
        }

        String sign = sb.substring(0, sb.lastIndexOf("&"));

        sign = DigestUtils.sha1Hex(sign);

        return sign;

    }
}
