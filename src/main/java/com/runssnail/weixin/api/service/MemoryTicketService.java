package com.runssnail.weixin.api.service;

import org.apache.commons.lang.StringUtils;

/**
 * 用本地内存来保存 ticket，单机可用，集群时请不要使用
 *
 * Created by zhengwei on 2016/3/31.
 */
public class MemoryTicketService extends AbstractTicketService {

    private volatile String ticket;

    @Override
    protected void saveTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String getTicket() {
        if (StringUtils.isBlank(this.ticket)) {
            return refresh();
        }
        return this.ticket;
    }
}
