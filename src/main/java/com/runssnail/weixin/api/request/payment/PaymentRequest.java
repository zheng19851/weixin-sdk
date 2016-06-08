package com.runssnail.weixin.api.request.payment;

import com.runssnail.weixin.api.request.PostRequest;
import com.runssnail.weixin.api.response.Response;

/**
 * 微信支付父类
 *
 * Created by zhengwei on 2016/3/24.
 */
public abstract class PaymentRequest<R extends Response> extends PostRequest<R> {
}
