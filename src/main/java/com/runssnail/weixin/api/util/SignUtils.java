package com.runssnail.weixin.api.util;

import com.runssnail.weixin.api.constant.SignType;
import com.runssnail.weixin.api.internal.util.MD5Util;
import com.runssnail.weixin.api.internal.util.XmlTool;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 微信支付签名工具类
 *
 * Created by zhengwei on 2016/3/17.
 */
public class SignUtils {

    private static final Log log = LogFactory.getLog(SignUtils.class);

    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 生成支付签名
     *
     * @param appId      微信公众号id，必填
     * @param prepayId   微信预支付单id，必填
     * @param paySignKey 支付密钥，必填
     * @return 支付签名
     */
    public static String buildPaySign(String appId, String prepayId, String paySignKey) {

        return buildPaySign(appId, prepayId, paySignKey, buildNonce(DEFAULT_CHARSET), System.currentTimeMillis() / 1000);
    }

    /**
     * 生成支付签名
     *
     * @param appId      微信公众号id，必填
     * @param prepayId   微信预支付单id，必填
     * @param paySignKey 支付密钥，必填
     * @param nonceStr   随机字符串，不超过32个字符，必填
     * @param timeStamp  时间戳，精确到秒，必填
     * @return 支付签名
     */
    public static String buildPaySign(String appId, String prepayId, String paySignKey, String nonceStr, long timeStamp) {

        SortedMap<String, Object> params = new TreeMap<String, Object>();
        params.put("appId", appId);
        params.put("timeStamp", String.valueOf(timeStamp));
        params.put("nonceStr", nonceStr);
        params.put("package", "prepay_id=" + prepayId);
        params.put("signType", SignType.MD5.getCode());
        return buildSign(params, paySignKey, SignType.MD5);
    }

    /**
     * 生成随机字符串
     *
     * @param charset 字符集
     * @return 随机字符串
     */
    public static String buildNonce(String charset) {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), charset); // "UTF-8"
    }

    /**
     * 生成随机字符串
     *
     * @return 随机字符串
     */
    public static String buildNonce() {
        return buildNonce(DEFAULT_CHARSET);
    }

    /**
     * 生成sign
     *
     * @param params 参数
     * @param key    签名密钥
     * @return 签名
     */
    public static String buildSign(Map<String, Object> params, String key) {

        return buildSign(params, key, SignType.MD5);

    }

    /**
     * 生成签名sign
     *
     * @param params   参数
     * @param key      签名密钥
     * @param signType 签名方式
     * @return
     */
    public static String buildSign(Map<String, Object> params, String key, SignType signType) {
        if (log.isDebugEnabled()) {
            log.debug("genSign, params=" + params + ", signType=" + signType);
        }

        StringBuilder sb = new StringBuilder();

        String paramsStr = buildUrlParamsStr(params, null);
        sb.append(paramsStr).append("&");
        sb.append("key=" + key);
        // System.out.println("genPackageSign params string=" + sb.toString());
        // String sign = MD5Util.MD5Encode(sb.toString(), charset)
        // .toUpperCase();
        String sign = null;
        if (signType.isMd5()) {
            sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        } else if (signType.isSHA1()) {
            sign = DigestUtils.sha1Hex(sb.toString()).toUpperCase();
        } else if (signType.isRsa()) {
            //
            throw new UnsupportedOperationException("unsupport current sign type, signType=" + signType);
        }

        if (log.isDebugEnabled()) {
            log.debug("genSign, sign=" + sign);
        }

        return sign;
    }

    public static String buildUrlParamsStr(Map<String, Object> packageParams, String charset) {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> es = packageParams.entrySet();
        for (Map.Entry<String, Object> entry : es) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (StringUtils.isNotBlank(k) && v != null && StringUtils.isNotBlank(v.toString())) {

                if (StringUtils.isNotBlank(charset) && v != null) {
                    v = urlEncode(v.toString(), charset);
                }
                sb.append(k + "=" + v + "&");
            }

        }

        String params = sb.substring(0, sb.lastIndexOf("&"));

        return params;
    }

    // 特殊字符处理
    private static String urlEncode(String src, String charset) {
        String str = null;
        try {
            str = URLEncoder.encode(src, charset).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder error, charset=" + charset, e);
        }
        return str;

    }

    public static boolean validateSign(String responseBody, String paySignKey) {
        Validate.notEmpty(responseBody);
        Validate.notEmpty(paySignKey);

        Map paramsMap = XmlTool.toMapStringValue(responseBody);
        if (!paramsMap.containsKey("sign")) {
            return true;
        }

        String sign = paramsMap.get("sign").toString();
        paramsMap.remove("sign");
        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>(paramsMap);

        String signFromParams = SignUtils.buildSign(sortedMap, paySignKey);

        if (log.isDebugEnabled()) {
            log.debug("validateSign, signFromParams=" + signFromParams + ", sign=" + sign);
        }

        return signFromParams.equals(sign);
    }
}
