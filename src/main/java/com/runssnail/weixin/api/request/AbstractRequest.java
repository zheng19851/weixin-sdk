package com.runssnail.weixin.api.request;

import com.runssnail.weixin.api.domain.BaseDomain;
import com.runssnail.weixin.api.exception.WeixinApiRuleException;
import com.runssnail.weixin.api.response.Response;
import org.apache.commons.lang.StringUtils;

import static com.runssnail.weixin.api.internal.support.WeixinApiRuleValidate.isTrue;
import static com.runssnail.weixin.api.internal.support.WeixinApiRuleValidate.notNull;

/**
 * {@link Request} 抽象实现
 *
 * @author zhengwei
 * @param <R>
 */
public abstract class AbstractRequest<R extends Response> extends BaseDomain implements Request<R> {

    /**
     *
     */
    private static final long serialVersionUID = -557258373506800680L;

    @Override
    public final void check() throws WeixinApiRuleException {
        isTrue(StringUtils.isNotBlank(getApiUrl()), "api url is required");
        notNull(getResponseClass(), "Response class is required");
        notNull(getMethod(), "RequestMethod is required");
        doCheck();
    }

    /**
     * 可以由子类实现
     */
    protected void doCheck() throws WeixinApiRuleException {
        // ignore
    }

}
