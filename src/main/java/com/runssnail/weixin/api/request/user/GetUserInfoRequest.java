package com.runssnail.weixin.api.request.user;

import com.runssnail.weixin.api.request.GetRequest;
import com.runssnail.weixin.api.response.user.GetUserInfoResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户基本信息
 *
 * Created by zhengwei on 16/5/30.
 */
public class GetUserInfoRequest extends GetRequest<GetUserInfoResponse> {

    /**
     * api授权凭证
     */
    private String              accessToken;

    /**
     * 微信openid
     */
    private String              openId;

    /**
     *
     * @param accessToken api授权凭证
     * @param openId openId
     */
    public GetUserInfoRequest(String accessToken, String openId) {
        this.accessToken = accessToken;
        this.openId = openId;
    }

    @Override
    public String getApiUrl() {
        return "https://api.weixin.qq.com/cgi-bin/user/info";
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("access_token", this.accessToken);
        params.put("openid", this.openId);
        params.put("lang", "zh_CN");
        return params;
    }

    @Override
    public Class<GetUserInfoResponse> getResponseClass() {
        return GetUserInfoResponse.class;
    }
}
