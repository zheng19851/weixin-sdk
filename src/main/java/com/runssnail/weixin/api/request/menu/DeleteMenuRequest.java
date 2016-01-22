package com.runssnail.weixin.api.request.menu;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.menu.DeleteMenuResponse;

import java.util.Map;

/**
 * 删除菜单
 */
public class DeleteMenuRequest extends GetRequest<DeleteMenuResponse> {

    /**
     * 
     */
    private static final long   serialVersionUID = 7257895097472433738L;

    private static final String API_URL          = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Class<DeleteMenuResponse> getResponseClass() {
        return DeleteMenuResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        return null;
    }

}
