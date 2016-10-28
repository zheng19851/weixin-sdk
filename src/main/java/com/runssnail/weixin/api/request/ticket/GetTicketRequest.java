package com.runssnail.weixin.api.request.ticket;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.ticket.GetTicketResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取ticket
 *
 * Created by zhengwei on 2016/10/28.
 */
public class GetTicketRequest extends GetRequest<GetTicketResponse> {

    /**
     *
     */
    private static final long serialVersionUID = 8550446338939906575L;

    private static final String GET_TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    /**
     * jsapi
     */
    private String type;

    public GetTicketRequest(String type) {
        this.type = type;
    }

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
        params.put("type", this.type);
        return params;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
