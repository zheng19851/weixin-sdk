package com.runssnail.weixin.api.service;

/**
 * jsapi ticket 服务
 *
 * Created by zhengwei on 2016/3/31.
 */
public interface TicketService {

    /**
     * 获取
     *
     * @return
     */
    String get();

    /**
     * 刷新
     *
     * @return
     */
    String refresh();
}
