package com.runssnail.weixin.api.request;

import com.alibaba.fastjson.JSON;
import com.runssnail.weixin.api.domain.BaseDomain;
import com.runssnail.weixin.api.exception.ApiRuleException;
import com.runssnail.weixin.api.internal.support.ApiRuleValidate;
import com.runssnail.weixin.api.response.Response;
import org.apache.commons.lang.StringUtils;


/**
 * {@link Request} 抽象实现
 *
 * @param <R>
 * @author zhengwei
 */
public abstract class AbstractRequest<R extends Response> extends BaseDomain implements Request<R> {

    /**
     *
     */
    private static final long serialVersionUID = -557258373506800680L;

    @Override
    public final void check() throws ApiRuleException {
        ApiRuleValidate.isTrue(StringUtils.isNotBlank(getApiUrl()), "api url is required");
        ApiRuleValidate.notNull(getResponseClass(), "Response class is required");
        ApiRuleValidate.notNull(getMethod(), "RequestMethod is required");
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
        // 默认当作字符串处理
        R res = JSON.parseObject(responseBody.toString(), getResponseClass());

        return res;
    }
}
