package com.runssnail.weixin.api.request.menu;

import com.alibaba.fastjson.annotation.JSONField;
import com.runssnail.weixin.api.domain.menu.Menu;
import com.runssnail.weixin.api.request.PostRequest;
import com.runssnail.weixin.api.response.menu.CreateMenuResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建菜单请求
 *
 * @author zhengwei
 */
public class CreateMenuRequest extends PostRequest<CreateMenuResponse> {

    /**
     *
     */
    private static final long serialVersionUID = -6565606701792297097L;

    private static final String API_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";

    /**
     * 菜单
     */
    private List<Menu> menus;

    public CreateMenuRequest(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    @JSONField(serialize = false)
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>(1);
        params.put("button", menus);
        return params;
    }

    @JSONField(serialize = false)
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    @JSONField(serialize = false)
    public Class<CreateMenuResponse> getResponseClass() {
        return CreateMenuResponse.class;
    }

}
