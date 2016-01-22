package com.runssnail.weixin.api.response.menu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.runssnail.weixin.api.domain.menu.ClickMenu;
import com.runssnail.weixin.api.domain.menu.Menu;
import com.runssnail.weixin.api.domain.menu.Menus;
import com.runssnail.weixin.api.domain.menu.NotFuncMenu;
import com.runssnail.weixin.api.domain.menu.SendLocationMenu;
import com.runssnail.weixin.api.domain.menu.ViewMenu;
import com.runssnail.weixin.api.response.JSONResponse;

/**
 * 获取菜单
 * 
 * @author zhengwei
 */
public class GetMenuResponse extends JSONResponse {

    /**
     * 
     */
    private static final long serialVersionUID = -1273118195134378261L;

    private Menus             menu;

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    private List<Menu> parseMenus(JSONArray jsonArr) {
        List<Menu> menus = new ArrayList<Menu>(jsonArr.size());
        for (Object obj : jsonArr) {
            JSONObject menuJson = (JSONObject) obj;
            Menu menu = parseFrom(menuJson);
            if (menu != null) {
                menus.add(menu);
            }
        }
        return menus;
    }

    private Menu parseFrom(JSONObject jsonObj) {
        Menu menu = newMenu(jsonObj);

        return menu;
    }

    private Menu newMenu(JSONObject jsonObj) {
        Menu menu = null;
        String name = jsonObj.getString("name");
        if (jsonObj.containsKey("type")) {
            String type = jsonObj.getString("type");
            if ("click".equals(type)) {
                String key = jsonObj.getString("key");
                menu = new ClickMenu(name, key);
            } else if ("view".equals(type)) {
                String url = jsonObj.getString("url");
                menu = new ViewMenu(name, url);
            } else if ("location_select".equals(type)) {
                String key = jsonObj.getString("key");
                menu = new SendLocationMenu(name, key);
            }
        } else {
            List<Menu> subMenus = parseMenus(jsonObj.getJSONArray("sub_button"));
            menu = new NotFuncMenu(name, subMenus);
        }
        return menu;
    }

    /**
     * ����һ���˵�
     * 
     * @return
     */
    public List<Menu> getMenus() {
        return this.menu.getButton();
    }

}
