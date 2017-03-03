package com.runssnail.weixin.api.request.pay;

import com.runssnail.weixin.api.domain.BaseDomain;
import com.runssnail.weixin.api.exception.ApiRuleException;
import com.runssnail.weixin.api.internal.support.ApiRuleValidate;
import com.runssnail.weixin.api.exception.PayApiException;
import com.runssnail.weixin.api.internal.support.WeixinPayResponseHelper;
import com.runssnail.weixin.api.response.pay.PayResponse;
import org.apache.commons.lang.StringUtils;

/**
 * 微信支付请求基类
 *
 * Created by zhengwei on 2017/3/3.
 */
public abstract class BasePayRequest<R extends PayResponse> extends BaseDomain implements PayRequest<R> {

    /**
     *
     */
    private static final long serialVersionUID = -557258373506800680L;

    @Override
    public final void check() throws ApiRuleException {
        ApiRuleValidate.isTrue(StringUtils.isNotBlank(getApiUrl()), "api url is required");
        ApiRuleValidate.notNull(getResponseClass(), "Response class is required");
//        ApiRuleValidate.notNull(getMethod(), "RequestMethod is required");
        doCheck();
    }

    /**
     * 可以由子类实现
     */
    protected void doCheck() throws ApiRuleException {
        // ignore
    }

    @Override
    public R buildResponse(Object responseBody) {
        R res;
        try {
            res = getResponseClass().newInstance();
        } catch (InstantiationException e) {
            throw new PayApiException(e);
        } catch (IllegalAccessException e) {
            throw new PayApiException(e);
        }
        if (res.getDataType().isXml()) {
            return (R) WeixinPayResponseHelper.getObjectFromXml(responseBody.toString(), res);
        } else {
            return res;
        }
    }

}
