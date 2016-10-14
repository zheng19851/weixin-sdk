package com.runssnail.weixin.api.test.message;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.domain.message.KeyNoteValue;
import com.runssnail.weixin.api.request.message.TemplateMessageRequest;
import com.runssnail.weixin.api.response.message.TemplateMessageResponse;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TemplateMessageRequestTest {

    private static final String DEFAULT_TITLE_COLOR = "#000000"; // 灰色BEBEBE

    private static final String DEFAULT_REMARK_COLOR = "#a6a6a6";

    private static final String DEFAULT_CONTENT_COLOR = "#000000";

    private static final String DEFAULT_MONEY_COLOR = "#FF0000"; // 红色


    public static void main(String[] args) {
        // 潘老板测试帐号
        String appId = "wx7cbc0121c2093f64";
        String appSecret = "5380b2231935166e7d0f02cdce8e7209";

        DefaultWeixinClient weixinClient = new DefaultWeixinClient(appId, appSecret);

//        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
//        accessTokenService.setWeiXinClient(weixinClient);


        try {

//            String token = accessTokenService.getAccessToken();
            String token = "uIFtGIuQ98tys_Z3iztlols2hBWiJUMj65JGUskWZ8QV1xDevzrwd46zmlcBlRRTW2_-AfmCs9pMc7IJe_7TUL_nqHy8JbQqaWG6nXUzq_Nqhizc9z0akWLNJ416Py2aIOEgACAQWM";
//            String token = "rCwnax5u6rpgdaiK-s90NpHv0S7kXjRsZmrL3ygA4yicsN5lkLarQCg7a8KfAXWrtOd7j8zL1TXQBzotkPuwaD7mwFfzVHSx5e7jXiW8S6HdLIxl2u_VshJX4M_u8iMnEBIgAAAXUB";
            TemplateMessageRequest req = createNotice();
            TemplateMessageResponse res = weixinClient.execute(req, token);
            System.out.println(res);
        } finally {
            if (weixinClient != null) {
                weixinClient.close();
            }
        }


    }

    private static TemplateMessageRequest createNotice() {
        Map<String, KeyNoteValue> data = new HashMap<String, KeyNoteValue>();
        data.put("first", new KeyNoteValue("先生您好", DEFAULT_TITLE_COLOR));
        data.put("keyword1", new KeyNoteValue("13888888888", DEFAULT_CONTENT_COLOR));
        data.put("keyword2", new KeyNoteValue("20元", DEFAULT_CONTENT_COLOR));
        data.put("keyword3", new KeyNoteValue("20元", DEFAULT_MONEY_COLOR));
        data.put("keyword4", new KeyNoteValue("2016-10-14", DEFAULT_MONEY_COLOR));
        data.put("remark", new KeyNoteValue("欢迎再次光临", DEFAULT_REMARK_COLOR));

        TemplateMessageRequest req = new TemplateMessageRequest("ROiM1nLeVIifQHMD07P1tjgXyNjyFDv17yxMm1ofehM",
                "oz1nRwi6wnQP9QVHEzNwpC1OnGuU", data);

        return req;

    }

    private static TemplateMessageRequest createAward() {


//        {{first.DATA}}
//        任务名称：{{keyword1.DATA}}
//        任务类别：{{keyword2.DATA}}
//        任务奖励金额：{{keyword3.DATA}}
//        {{remark.DATA}}

        Map<String, KeyNoteValue> data = new HashMap<String, KeyNoteValue>();
        data.put("first", new KeyNoteValue("独家,飞机啊浪费发啦发酒疯啦解放啦激发家里附近", DEFAULT_TITLE_COLOR));
        data.put("keyword1", new KeyNoteValue("【独家】并购重组监管最新五大方向曝光：市场热点答案出齐了", DEFAULT_CONTENT_COLOR));
        data.put("keyword2", new KeyNoteValue("学习奖励", DEFAULT_CONTENT_COLOR));
        data.put("keyword3", new KeyNoteValue("39.8个铜板", DEFAULT_MONEY_COLOR));
        data.put("remark", new KeyNoteValue("欢迎再次光临", DEFAULT_REMARK_COLOR));

        TemplateMessageRequest req = new TemplateMessageRequest("r-_xywMwK-BfTy8A4JJNpkwB4VgHaX-VZ6nZg54D24M",
                "oeumFjrOrsYmEV-MAElyRnscFwoo", data);

        return req;
    }

    static TemplateMessageRequest createOrder() {
        Map<String, KeyNoteValue> data = new HashMap<String, KeyNoteValue>();
        data.put("first", new KeyNoteValue("购买成功", "#BEBEBE"));
        data.put("keyword1", new KeyNoteValue("亲爱的，李先生\n" + build("任务名称: ", "亲爱的，李先生亲爱的，李先生亲爱的，李先生亲爱的，李先生"), "#BEBEBE"));
        data.put("keyword2", new KeyNoteValue("39.8", "#BEBEBE"));
        data.put("remark", new KeyNoteValue("欢迎再次光临", "#BEBEBE"));

        TemplateMessageRequest req = new TemplateMessageRequest("KRZM_j4dDvEj3khkluna67BMT14RA59o_NZVq7JpqzI",
                "oeumFjrOrsYmEV-MAElyRnscFwoo", data);

        return req;
    }


    static String build(String fieldName, String content) {
        int appendCount = 0;
        try {
            appendCount = fieldName.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {
            // ignore
        }

        return StringUtils.leftPad(content, content.length() + appendCount, " ");


    }


}
