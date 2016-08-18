package com.runssnail.weixin.api.request.web;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.web.GetUserResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 网页开发-获取用户请求
 *
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842&token=&lang=zh_CN
 *
 * @author zhengwei
 */
public class GetUserRequest extends GetRequest<GetUserResponse> {

    /**
     *
     */
    private static final long   serialVersionUID = 4387424394747555913L;

    private static final String API_URL          = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 网页授权时，获取的accessToken，非api授权凭证
     */
    private String              accessToken;

    /**
     * 微信openid
     */
    private String              openId;

    public GetUserRequest(String accessToken, String openId) {
        this.accessToken = accessToken;
        this.openId = openId;
    }

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    @Override
    public Class<GetUserResponse> getResponseClass() {
        return GetUserResponse.class;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("access_token", this.accessToken);
        params.put("openid", this.openId);
        params.put("lang", "zh_CN");
        return params;
    }

}
