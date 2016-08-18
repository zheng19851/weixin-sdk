package com.runssnail.weixin.api.request.web;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.web.GetJsApiTicketResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * js sdk 时用到的ticket
 *
 * @author zhengwei
 */
public class GetJsApiTicketRequest extends GetRequest<GetJsApiTicketResponse> {

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
    public Class<GetJsApiTicketResponse> getResponseClass() {
        return GetJsApiTicketResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>(1);
        params.put("type", "jsapi");
        return params;
    }

}
