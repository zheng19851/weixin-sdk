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
     * 微信openid
     */
    private String              openId;

    /**
     *
     * @param openId openId
     */
    public GetUserInfoRequest(String openId) {
        this.openId = openId;
    }

    @Override
    public String getApiUrl() {
        return "https://api.weixin.qq.com/cgi-bin/user/info";
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("openid", this.openId);
        params.put("lang", "zh_CN");
        return params;
    }

    @Override
    public Class<GetUserInfoResponse> getResponseClass() {
        return GetUserInfoResponse.class;
    }
}
