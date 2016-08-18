package com.runssnail.weixin.api.service;

/**
 * jsapi ticket 服务
 *
 * Created by zhengwei on 2016/3/31.
 */
public interface JsApiTicketService {

    /**
     * 获取
     *
     * @return
     */
    String getTicket();

    /**
     * 刷新
     *
     * @return
     */
    String refresh();
}
