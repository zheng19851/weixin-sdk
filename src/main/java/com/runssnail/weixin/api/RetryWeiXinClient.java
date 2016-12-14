package com.runssnail.weixin.api;

import com.runssnail.weixin.api.exception.ApiException;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.Set;

/**
 * 调用出错时自动重试
 * <p>
 * Created by zhengwei on 2016/3/18.
 */
public class RetryWeiXinClient implements WeixinClient {

    protected final Log log = LogFactory.getLog(getClass());

    private static final ApiException RETRY_FAIL = new ApiException("sdk.retry-call-fail:API调用重试失败");

    /**
     * 单次请求的最大重试次数，默认值为3次。
     */
    private int maxRetryCount = 3;

    /**
     * 重试之前休眠时间，默认值为100毫秒。
     */
    private long retryWaitTime = 100L;

    /**
     * 超过最大重试次数时是否抛出异常。
     */
    private boolean throwIfOverMaxRetry = false;
    /**
     * 自定义重试错误码列表。
     */
    private Set<String> retryErrorCodes;

    /**
     * 实际的委托服务对象
     */
    private WeixinClient weiXinClient;

    public RetryWeiXinClient() {
    }

    public RetryWeiXinClient(WeixinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public WeixinClient getWeiXinClient() {
        return weiXinClient;
    }

    public void setWeiXinClient(WeixinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    @Override
    public String getAppId() {
        return this.weiXinClient.getAppId();
    }

    @Override
    public String getAppSecret() {
        return weiXinClient.getAppSecret();
    }

    public <T extends Response> T execute(final Request<T> request) throws ApiException {
        return this.executeInternal(request, new Callback<T>() {
            @Override
            public T callback() {
                return weiXinClient.execute(request);
            }
        });
    }

    public <T extends Response> T execute(final Request<T> request, final String token) throws ApiException {
        return executeInternal(request, new Callback<T>() {
            @Override
            public T callback() {
                return weiXinClient.execute(request, token);
            }
        });
    }

    private <T extends Response> T executeInternal(Request<T> request, Callback<T> callback) {
        T rsp = null;
        ApiException exp = null;

        for (int i = 0; i <= maxRetryCount; i++) {
            if (i > 0) {
                if ((rsp != null && ((retryErrorCodes != null && retryErrorCodes.contains(rsp.getErrcode())))) || exp != null) {
                    sleepWithoutInterrupt(retryWaitTime);
                    log.warn(buildRetryLog(request.getApiUrl(), request.getParams(), i));
                } else {
                    break;
                }
            }

            try {
                rsp = callback.callback();
                if (rsp.isSuccess()) {
                    return rsp;
                } else {
                    if (i == maxRetryCount && throwIfOverMaxRetry) {
                        throw RETRY_FAIL;
                    }
                }
            } catch (ApiException e) {
                if (exp == null) {
                    exp = e;
                }
            }
        }

        if (exp != null) {
            throw exp;
        } else {
            return rsp;
        }
    }

    private interface Callback<T> {
        T callback();
    }

    private String buildRetryLog(String apiUrl, Map<String, Object> params, int retryCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(apiUrl).append(" retry call ").append(retryCount);
        params.remove("fields");
        sb.append(" times, params=").append(params);
        return sb.toString();
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public void setRetryWaitTime(long retryWaitTime) {
        this.retryWaitTime = retryWaitTime;
    }

    public void setThrowIfOverMaxRetry(boolean throwIfOverMaxRetry) {
        this.throwIfOverMaxRetry = throwIfOverMaxRetry;
    }

    public void setRetryErrorCodes(Set<String> retryErrorCodes) {
        this.retryErrorCodes = retryErrorCodes;
    }

    private void sleepWithoutInterrupt(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }
}
