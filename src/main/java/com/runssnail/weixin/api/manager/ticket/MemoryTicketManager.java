package com.runssnail.weixin.api.manager.ticket;

import com.runssnail.weixin.api.domain.ticket.TicketDO;

/**
 * 用本地内存来保存 ticket，单机可用，集群时请不要使用
 * <p>
 * Created by zhengwei on 2016/3/31.
 */
public class MemoryTicketManager extends AbstractTicketManager {

    private volatile TicketDO ticket;

    @Override
    protected void storeValue(TicketDO valueDO) {
        this.ticket = valueDO;
    }

    @Override
    public TicketDO getValue() {
        return this.ticket;
    }
}
