package com.runssnail.weixin.api;

import com.runssnail.weixin.api.constant.SignType;
import com.runssnail.weixin.api.util.SignUtils;
import com.runssnail.weixin.api.constant.Constants;
import com.runssnail.weixin.api.internal.annotations.AppIdWired;
import com.runssnail.weixin.api.internal.annotations.MerchantIdWired;
import com.runssnail.weixin.api.internal.http.DefaultHttpClient;
import com.runssnail.weixin.api.internal.http.HttpClient;
import com.runssnail.weixin.api.internal.util.XmlTool;
import com.runssnail.weixin.api.exception.PayApiException;
import com.runssnail.weixin.api.internal.http.PayHttpClient;
import com.runssnail.weixin.api.request.pay.PayRequest;
import com.runssnail.weixin.api.response.pay.PayResponse;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * 默认的微信支付api client
 * <p>
 * Created by zhengwei on 2015/11/6.
 */
public class DefaultWeixinPayClient implements WeixinPayClient {

    private static final Log log = LogFactory.getLog(DefaultWeixinPayClient.class);

    /**
     * 连接超时时间，单位毫秒，默认3秒
     */
    private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

    /**
     * 读取超时时间，单位毫秒，默认10秒
     */
    private int readTimeout = Constants.DEFAULT_READ_TIMEOUT;

    private String appId;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 微信支付签名秘钥
     */
    private String paySignKey;

    private HttpClient httpClient;

    /**
     * 创建DefaultWeiXinPaymentClient
     *
     * @param appId      微信公众号id
     * @param mchId      商户号
     * @param paySignKey 支付秘钥
     */
    public DefaultWeixinPayClient(String appId, String mchId, String paySignKey) {
        this.appId = appId;
        this.mchId = mchId;
        this.paySignKey = paySignKey;
        this.httpClient = new DefaultHttpClient(connectTimeout, readTimeout);
    }

    /**
     * 创建DefaultWeiXinPaymentClient
     *
     * @param appId        微信公众号id
     * @param mchId        商户号
     * @param paySignKey   支付秘钥
     * @param certPath     证书路径
     * @param certPassword 证书密码
     */
    public DefaultWeixinPayClient(String appId, String mchId, String paySignKey, String certPath, String certPassword) {
        this.appId = appId;
        this.mchId = mchId;
        this.paySignKey = paySignKey;

        this.httpClient = new PayHttpClient(certPath, certPassword); //new HttpsClient(certPath, certPassword);
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPaySignKey() {
        return paySignKey;
    }

    public void setPaySignKey(String paySignKey) {
        this.paySignKey = paySignKey;
    }

    /**
     * 将post参数转换成字符串
     *
     * @param request
     * @return
     */
    protected <R extends PayResponse> String buildPostParams(PayRequest<R> request) {

        Map<String, Object> params = request.getParams();


        if (request.getClass().isAnnotationPresent(AppIdWired.class)) {
            AppIdWired appIdWired = request.getClass().getAnnotation(AppIdWired.class);
            Validate.notEmpty(appIdWired.value(), "appIdKey is required");
            params.put(appIdWired.value(), this.appId);

        } else {
            if (!params.containsKey("appid")) {
                params.put("appid", this.appId);
            }
        }

        if (request.getClass().isAnnotationPresent(MerchantIdWired.class)) {
            MerchantIdWired merchantIdWired = request.getClass().getAnnotation(MerchantIdWired.class);
            params.put(merchantIdWired.value(), this.mchId);
        } else {

            if (!params.containsKey("mch_id")) {
                params.put("mch_id", this.mchId);
            }

        }

        if (!params.containsKey("nonce_str")) {
            params.put("nonce_str", SignUtils.buildNonce());
        }

        // 创建sign
        String sign = SignUtils.buildSign(params, this.paySignKey, SignType.MD5);
        params.put("sign", sign);

        return XmlTool.toXml(params);
    }

    protected <R extends PayResponse> R buildResponse(String result, PayRequest<R> req) {

        return req.buildResponse(result);
    }

    /**
     * 校验响应数据
     *
     * @param res
     * @param <R>
     */
    protected <R extends PayResponse> void checkResponse(R res) {
        res.check(this.paySignKey);
    }


    @Override
    public <R extends PayResponse> R execute(PayRequest<R> req) throws PayApiException {
        Validate.notNull(req, "request is required");

        return executeInternal(req);
    }

    /**
     * 执行请求
     *
     * @param req 请求
     * @param <R> 响应对象
     * @return 响应对象
     */
    private <R extends PayResponse> R executeInternal(PayRequest<R> req) {
        String apiUrl = req.getApiUrl();

        if (log.isDebugEnabled()) {
            log.debug("execute start, apiUrl=" + apiUrl + ", request=" + req);
        }

        long start = System.currentTimeMillis();

        req.check();

        String result = this.httpClient.doPost(apiUrl, buildPostParams(req));

        if (log.isDebugEnabled()) {
            log.debug("execute request finished, used total " + (System.currentTimeMillis() - start) + " ms, apiUrl=" + apiUrl + ", request=" + req + ", result=" + result);
        }

        R res = buildResponse(result, req);

        res.setResponseBody(result);

        checkResponse(res);

        if (!res.isSuccess()) {
            log.error("execute request error, apiUrl=" + apiUrl + ", request=" + req + ", response=" + res);
        }

        if (log.isDebugEnabled()) {
            log.debug("execute end, used total " + (System.currentTimeMillis() - start) + " ms, response=" + res);
        }
        return res;
    }

    @Override
    public String getAppId() {
        return this.appId;
    }

    /**
     * http 连接超时时间，单位毫秒
     *
     * @return http 连接超时时间，单位毫秒
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * 设置 http 连接超时时间，单位毫秒
     *
     * @param connectTimeout http 连接超时时间，单位毫秒
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * http 读取时间，单位毫秒
     *
     * @return http 读取时间，单位毫秒
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * 设置http 读取时间，单位毫秒
     *
     * @param readTimeout http 读取时间，单位毫秒
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void init() {
        this.httpClient.init();
    }

    public void close() {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
