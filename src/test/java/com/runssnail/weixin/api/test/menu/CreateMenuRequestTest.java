package com.runssnail.weixin.api.test.menu;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.domain.menu.ClickMenu;
import com.runssnail.weixin.api.domain.menu.Menu;
import com.runssnail.weixin.api.domain.menu.NotFuncMenu;
import com.runssnail.weixin.api.domain.menu.ViewMenu;
import com.runssnail.weixin.api.request.menu.CreateMenuRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeiXinApiClients;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengwei
 * @see CreateMenuRequestTest2
 */
@Deprecated
public class CreateMenuRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeiXinClient weixinApiClient = WeiXinApiClients.buildRetryWeixinClient(appId, appSecret);

        List<Menu> menus = new ArrayList<Menu>();

        Menu click = new ClickMenu("���ո���11", "fajflajlf");
        menus.add(click);

        List<Menu> subMenus = new ArrayList<Menu>();
        Menu viewMenu1 = new ViewMenu("����11", "http://www.soso.com/");
        subMenus.add(viewMenu1);
        Menu click1 = new ClickMenu("��һ������1", "fajfewwwlajlf");
        subMenus.add(click1);

        Menu notFunc = new NotFuncMenu("�˵�1", subMenus);

        menus.add(notFunc);

        CreateMenuRequest req = new CreateMenuRequest(menus);

        Response res = weixinApiClient.execute(req, true);

        System.out.println(res);
        weixinApiClient.close();

    }

}
