package com.runssnail.weixin.api.test.template;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.domain.template.KeyNoteValue;
import com.runssnail.weixin.api.request.template.TemplateMessageRequest;
import com.runssnail.weixin.api.response.template.TemplateMessageResponse;
import com.runssnail.weixin.api.support.WeiXinApiClients;

import java.util.HashMap;
import java.util.Map;

public class TemplateMessageRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeiXinClient weixinApiClient = null;

        try {
            weixinApiClient = WeiXinApiClients.buildRetryWeixinClient(appId, appSecret);
            Map<String, KeyNoteValue> data = new HashMap<String, KeyNoteValue>();
            data.put("first", new KeyNoteValue("购买成功"));
            data.put("keyword1", new KeyNoteValue("亲爱的，李先生"));
            data.put("keyword2", new KeyNoteValue("39.8Ԫ"));
            data.put("remark", new KeyNoteValue("欢迎再次光临"));

            TemplateMessageRequest req = new TemplateMessageRequest("KRZM_j4dDvEj3khkluna67BMT14RA59o_NZVq7JpqzI",
                                                                    "oeumFjrOrsYmEV-MAElyRnscFwoo", data);

            TemplateMessageResponse res = weixinApiClient.execute(req, true);
            System.out.println(res);
        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }


    }

}
