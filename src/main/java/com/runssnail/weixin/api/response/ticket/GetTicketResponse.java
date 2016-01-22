package com.runssnail.weixin.api.response.ticket;

import com.runssnail.weixin.api.response.JSONResponse;

/**
 * js api时用到
 * 
 * @author zhengwei
 *
 */
public class GetTicketResponse extends JSONResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 3602255493120477549L;

    private String            ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
