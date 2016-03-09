package com.runssnail.weixin.api.test.menu;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.domain.menu.Menu;
import com.runssnail.weixin.api.request.menu.CreateMenuRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeiXinClients;

import java.util.ArrayList;
import java.util.List;

public class CreateMenuRequestTest2 {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeiXinClient weixinApiClient = null;
        try {
            weixinApiClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            List<Menu> menus = new ArrayList<Menu>();

            Menu click = new Menu("click", "歌曲");
            click.setKey("fajflajlf");
            menus.add(click);

            List<Menu> subMenus = new ArrayList<Menu>();
            Menu viewMenu1 = new Menu("view", "搜索");
            viewMenu1.setUrl("http://www.soso.com/");
            subMenus.add(viewMenu1);

            Menu click1 = new Menu("click", "2级");
            click1.setKey("fajflajlaaaaf");
            subMenus.add(click1);

            Menu notFunc = new Menu("一级菜单", subMenus);

            menus.add(notFunc);

            CreateMenuRequest req = new CreateMenuRequest(menus);

            Response res = weixinApiClient.execute(req, true);

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
