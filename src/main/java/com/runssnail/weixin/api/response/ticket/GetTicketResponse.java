package com.runssnail.weixin.api.response.ticket;

import com.runssnail.weixin.api.response.JSONResponse;

/**
 * ticket response
 *
 * Created by zhengwei on 2016/10/28.
 */
public class GetTicketResponse extends JSONResponse {

    /**
     *
     */
    private static final long serialVersionUID = 3602255493120477549L;

    /**
     * ticket
     */
    private String            ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
