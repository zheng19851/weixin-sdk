package com.runssnail.weixin.api;

import com.runssnail.weixin.api.common.SignType;
import com.runssnail.weixin.api.common.utils.SignUtils;
import com.runssnail.weixin.api.constants.Constants;
import com.runssnail.weixin.api.exception.WeiXinApiException;
import com.runssnail.weixin.api.internal.support.WeixinApiRuleValidate;
import com.runssnail.weixin.api.internal.support.WeixinPayResponseHelper;
import com.runssnail.weixin.api.internal.utils.HttpsClient;
import com.runssnail.weixin.api.internal.utils.XmlTool;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.response.payment.PaymentResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * 默认的微信支付api client
 *
 * Created by zhengwei on 2015/11/6.
 */
public class DefaultWeiXinPaymentClient implements WeiXinPaymentClient {

    private static final Log log = LogFactory.getLog(DefaultWeiXinPaymentClient.class);

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

    /**
     * https client
     */
    private HttpsClient httpsClient;

    /**
     * 创建DefaultWechatPaymentClient
     *
     * @param appId 微信公众号id
     * @param mchId 商户号
     * @param paySignKey 支付秘钥
     * @param certPath 证书路径
     * @param certPassword  证书密码
     */
    public DefaultWeiXinPaymentClient(String appId, String mchId, String paySignKey, String certPath, String certPassword) {
        this.appId = appId;
        this.mchId = mchId;
        this.paySignKey = paySignKey;

        this.httpsClient = new HttpsClient(certPath, certPassword);
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
     * @param params
     * @return
     */
    protected String buildPostParams(Map<String, Object> params) {

        // 创建sign
        String sign = SignUtils.buildSign(params, this.paySignKey, SignType.MD5);
        params.put("sign", sign);

        return XmlTool.toXml(params);
    }

    protected <R extends Response> R buildResponse(String result, Request<R> req) {
        return WeixinPayResponseHelper.getObjectFromXml(result, req.getResponseClass());
    }

    /**
     * 校验响应数据
     *
     * @param res
     * @param <R>
     */
    protected <R extends Response> void checkResponse(R res) {
        ((PaymentResponse) res).check(this.paySignKey);
    }


    @Override
    public <R extends Response> R execute(Request<R> req) throws WeiXinApiException {
        WeixinApiRuleValidate.notNull(req, "request is required");

        return executeInternal(req);
    }

    /**
     * 执行请求
     *
     * @param req    请求
     * @param <R>    响应对象
     * @return 响应对象
     */
    private <R extends Response> R executeInternal(Request<R> req) {
        String apiUrl = req.getApiUrl();

        if (log.isDebugEnabled()) {
            log.debug("execute start, apiUrl=" + apiUrl + ", request=" + req);
        }

        long start = System.currentTimeMillis();

        req.check();

        String result = httpsClient.doPost(apiUrl, buildPostParams(req.getParams()));

        if (log.isDebugEnabled()) {
            log.debug("execute request success, apiUrl=" + apiUrl + ", request=" + req + ", result=" + result);
        }

        R res = buildResponse(result, req);

        res.setResponseBody(result);

        if (res.isSuccess()) {
            checkResponse(res);
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

    @Override
    public void init() {

    }

    @Override
    public void close() {
        if (httpsClient != null) {
            httpsClient.close();
        }
    }
}
