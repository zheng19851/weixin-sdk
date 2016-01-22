package com.runssnail.weixin.api.request.menu;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.menu.GetMenuResponse;

import java.util.Map;

/**
 * 获取菜单
 * 
 * @author zhengwei
 */
public class GetMenuRequest extends GetRequest<GetMenuResponse> {

    /**
     * 
     */
    private static final long   serialVersionUID = 3224474222401864977L;

    private static final String API_URL          = "https://api.weixin.qq.com/cgi-bin/menu/get";

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Class<GetMenuResponse> getResponseClass() {
        return GetMenuResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        return null;
    }

}
