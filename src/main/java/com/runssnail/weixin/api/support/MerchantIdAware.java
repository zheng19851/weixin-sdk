package com.runssnail.weixin.api.support;

/**
 * 自动注入商户ID
 *
 * 你妹的,微信企业付款接口里的商户ID参数名称还和其他的不一致
 *
 * Created by zhengwei on 16/6/1.
 */
public interface MerchantIdAware {

    String getMerchantIdKey();

}
