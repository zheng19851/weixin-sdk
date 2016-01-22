package com.runssnail.weixin.api.request;

import com.runssnail.weixin.api.common.RequestMethod;
import com.runssnail.weixin.api.response.Response;

/**
 * post 请求
 *
 * @param <R>
 */
public abstract class PostRequest<R extends Response> extends AbstractRequest<R> {

    /**
     * 
     */
    private static final long serialVersionUID = 2725157683268125555L;

    @Override
    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

}
