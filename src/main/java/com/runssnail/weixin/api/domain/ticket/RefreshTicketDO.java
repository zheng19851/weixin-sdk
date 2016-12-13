package com.runssnail.weixin.api.domain.ticket;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * Created by zhengwei on 2016/12/13.
 */
public class RefreshTicketDO extends BaseDomain {

    private String ticket;

    private String oldTicket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getOldTicket() {
        return oldTicket;
    }

    public void setOldTicket(String oldTicket) {
        this.oldTicket = oldTicket;
    }
}
