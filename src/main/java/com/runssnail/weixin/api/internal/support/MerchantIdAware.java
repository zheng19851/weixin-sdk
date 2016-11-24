package com.runssnail.weixin.api.internal.support;

/**
 * 自动注入商户ID
 *
 * 你妹的,微信企业付款接口里的商户ID参数名称还和其他的不一致
 *
 * @see com.runssnail.weixin.api.internal.annotations.MerchantIdWired
 *
 * Created by zhengwei on 16/6/1.
 */
@Deprecated
public interface MerchantIdAware {

    String getMerchantIdKey();

}
