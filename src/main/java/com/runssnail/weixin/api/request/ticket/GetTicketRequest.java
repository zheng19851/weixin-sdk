package com.runssnail.weixin.api.request.ticket;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.ticket.GetTicketResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * js sdk 时用到的ticket
 *
 * @author zhengwei
 */
public class GetTicketRequest extends GetRequest<GetTicketResponse> {

    /**
     *
     */
    private static final long   serialVersionUID = 8550446338939906575L;

    private static final String GET_TICKET_API   = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    @Override
    public String getApiUrl() {
        return GET_TICKET_API;
    }

    @Override
    public Class<GetTicketResponse> getResponseClass() {
        return GetTicketResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>(1);
        params.put("type", "jsapi");
        return params;
    }

}
