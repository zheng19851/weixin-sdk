package com.runssnail.weixin.api.response.web;

import com.runssnail.weixin.api.response.JSONResponse;

/**
 * js api时用到
 * 
 * @author zhengwei
 *
 */
public class GetJsApiTicketResponse extends JSONResponse {

    /**
     * 
     */
    private static final long serialVersionUID = 3602255493120477549L;

    /**
     * jsapi_ticket
     */
    private String            ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
