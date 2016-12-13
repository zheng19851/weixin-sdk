package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.common.Result;
import com.runssnail.weixin.api.domain.ticket.RefreshTicketDO;

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
     * @return 新的ticket
     */
    String refresh();

    /**
     * 刷新ticket，并且返回新的ticket
     *
     * @return 新的ticket
     */
    Result<RefreshTicketDO> refreshAndGet();
}
