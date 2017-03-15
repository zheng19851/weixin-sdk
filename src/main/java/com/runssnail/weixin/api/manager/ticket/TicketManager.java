package com.runssnail.weixin.api.manager.ticket;

import com.runssnail.weixin.api.domain.ticket.TicketDO;
import com.runssnail.weixin.api.manager.ValueManager;

/**
 * ticket 服务
 * <p>
 * Created by zhengwei on 2016/3/31.
 */
public interface TicketManager extends ValueManager<TicketDO> {

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


}
