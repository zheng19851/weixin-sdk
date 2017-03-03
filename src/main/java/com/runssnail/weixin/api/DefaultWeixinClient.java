package com.runssnail.weixin.api;

import com.alibaba.fastjson.JSON;
import com.runssnail.weixin.api.constant.RequestMethod;
import com.runssnail.weixin.api.constant.Constants;
import com.runssnail.weixin.api.domain.FileItem;
import com.runssnail.weixin.api.exception.ApiException;
import com.runssnail.weixin.api.internal.annotations.AppIdWired;
import com.runssnail.weixin.api.internal.annotations.AppSecretWired;
import com.runssnail.weixin.api.internal.http.DefaultHttpClient;
import com.runssnail.weixin.api.internal.http.HttpClient;
import com.runssnail.weixin.api.request.DownloadRequest;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.request.UploadRequest;
import com.runssnail.weixin.api.response.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * 默认的WeiXinApiClient服务实现
 *
 * @author zhengwei
 * @see WeixinClient
 */
public class DefaultWeixinClient implements WeixinClient {

    protected final Log log = LogFactory.getLog(getClass());

    private final static String ACCESS_TOKEN = "access_token";

    /**
     * 连接超时时间，单位毫秒，默认3秒
     */
    private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

    /**
     * 读取超时时间，单位毫秒，默认10秒
     */
    private int readTimeout = Constants.DEFAULT_READ_TIMEOUT;

    /**
     * 微信app id
     */
    private String appId;

    /**
     * 微信appSecret
     */
    private String appSecret;

    private HttpClient httpClient = new DefaultHttpClient(connectTimeout, readTimeout);

    /**
     * 创建DefaultWeiXinApiClient
     *
     * @param appId     appId
     * @param appSecret appSecret
     */
    public DefaultWeixinClient(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override
    public <R extends Response> R execute(Request<R> req) throws ApiException {
        Validate.notNull(req, "request is required");

        return executeInternal(req.getApiUrl(), req);
    }

    @Override
    public <R extends Response> R execute(Request<R> req, String accessToken) throws ApiException {

        Validate.notNull(req, "request is required");
        Validate.notEmpty(accessToken, "accessToken is required");

        String apiUrl = buildApiUrl(req, accessToken);

        return executeInternal(apiUrl, req);
    }

    /**
     * 执行请求
     *
     * @param apiUrl 实际的apiurl
     * @param req    请求
     * @param <R>    响应对象
     * @return 响应对象
     */
    private <R extends Response> R executeInternal(String apiUrl, Request<R> req) {

        if (log.isDebugEnabled()) {
            log.debug("execute start, appId=" + this.appId + ", apiUrl=" + apiUrl + ", request=" + req);
        }

        long start = System.currentTimeMillis();

        req.check();

        RequestMethod method = req.getMethod();

        // String result = StringUtils.EMPTY;

        Object result = null;
        Map<String, Object> params = prepareParams(req);

        try {
            if (method.isGet()) {

                if (req instanceof DownloadRequest) {
                    result = httpClient.doGetFile(apiUrl, params);
                } else {
                    result = httpClient.doGet(apiUrl, params);
                }

            } else {

                if (req instanceof UploadRequest) {
                    UploadRequest uploadRequest = (UploadRequest) req;
                    Map<String, FileItem> fileParams = uploadRequest.getFileParams();
                    result = httpClient.doPost(apiUrl, params, fileParams, this.connectTimeout, this.readTimeout);
                } else {
                    result = httpClient.doPost(apiUrl, buildPostParams(params), this.connectTimeout, this.readTimeout);
                }

            }

            if (log.isDebugEnabled()) {
                log.debug("execute request finished, appId=" + this.appId + ", used total " + (System.currentTimeMillis() - start) + " ms, apiUrl=" + apiUrl + ", request=" + req + ", result=" + result);
            }

        } catch (Exception e) {
            throw new ApiException("execute request error, appId=" + this.appId + ", apiUrl=" + apiUrl + ", request=" + req + ", result=" + result, e);
        }

        R res = buildResponse(result, req);

        res.setResponseBody(result);

        checkResponse(res);

        if (!res.isSuccess()) {
            log.error("execute request error, appId=" + this.appId + ", apiUrl=" + apiUrl + ", request=" + req + ", response=" + res);
        }

        if (log.isDebugEnabled()) {
            log.debug("execute end, appId=" + this.appId + ", used total " + (System.currentTimeMillis() - start) + " ms, response=" + res);
        }
        return res;
    }

    /**
     * 预处理下请求参数
     * <p>
     * 注入appid和appSecret
     *
     * @param req 请求
     * @param <R>
     * @return
     */
    private <R extends Response> Map<String, Object> prepareParams(Request<R> req) {

        Map<String, Object> params = req.getParams();

        if (req.getClass().isAnnotationPresent(AppIdWired.class)) {
            AppIdWired appIdWired = req.getClass().getAnnotation(AppIdWired.class);
            params.put(appIdWired.value(), this.appId);
        }

        if (req.getClass().isAnnotationPresent(AppSecretWired.class)) {
            AppSecretWired appSecretWired = req.getClass().getAnnotation(AppSecretWired.class);
            params.put(appSecretWired.value(), this.appSecret);
        }


        return params;
    }

    /**
     * 检查响应数据是否正确，微信支付需要验证签名
     *
     * @param res
     * @param <R>
     */
    protected <R extends Response> void checkResponse(R res) {

    }

    /**
     * 将响应数据生成对象
     *
     * @param result 响应字符串
     * @param req    请求对象
     * @param <R>    响应对象
     * @return 响应对象
     */
    protected <R extends Response> R buildResponse(Object result, Request<R> req) {
        //return JSON.parseObject(result, req.getResponseClass());
        return req.buildResponse(result);
    }

    /**
     * 生成post数据，默认生成json数据
     *
     * @param params
     * @return
     */
    protected String buildPostParams(Map<String, Object> params) {
        return JSON.toJSONString(params);
    }

    /**
     * 生成实际的api url，access token 不为空的话，自动加上
     *
     * @param req         api请求
     * @param accessToken accessToken
     * @return api url
     */
    private <R extends Response> String buildApiUrl(Request<R> req, String accessToken) {
        String apiUrl = req.getApiUrl();

        if (StringUtils.isBlank(accessToken)) {
            return apiUrl;
        }

        StringBuilder apiUrlBuilder = new StringBuilder(apiUrl);
        if (!apiUrl.contains("?")) {
            apiUrlBuilder.append("?");
        } else if (!apiUrl.endsWith("&")) {
            apiUrlBuilder.append("&");
        }
        apiUrlBuilder.append(ACCESS_TOKEN).append("=").append(accessToken);
        apiUrl = apiUrlBuilder.toString();
        return apiUrl;
    }

    @Override
    public String getAppId() {
        return this.appId;
    }

    @Override
    public String getAppSecret() {
        return this.appSecret;
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
        this.httpClient.init();
    }

    @Override
    public void close() {
        this.httpClient.close();
    }
}
