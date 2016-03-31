package com.runssnail.weixin.api.service;

/**
 * 用本地内存来保存 ticket，单机可用，集群时请不要使用
 *
 * Created by zhengwei on 2016/3/31.
 */
public class MemoryTicketService extends AbstractTicketService {

    private volatile String ticket;

    @Override
    protected void save(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String get() {
        return this.ticket;
    }
}
