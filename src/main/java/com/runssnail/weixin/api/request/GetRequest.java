package com.runssnail.weixin.api.request;

import com.runssnail.weixin.api.constant.RequestMethod;
import com.runssnail.weixin.api.response.Response;

/**
 * get 请求
 * 
 * @author zhengwei
 *
 * @param <R>
 */
public abstract class GetRequest<R extends Response> extends AbstractRequest<R> {

    /**
     * 
     */
    private static final long serialVersionUID = -6337731787386612770L;

    @Override
    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

}
