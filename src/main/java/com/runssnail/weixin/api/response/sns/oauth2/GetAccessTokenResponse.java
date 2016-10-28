package com.runssnail.weixin.api.response.sns.oauth2;

import com.alibaba.fastjson.annotation.JSONField;
import com.runssnail.weixin.api.response.JSONResponse;

/**
 * 获取网页授权用的access token
 * <p>
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842&token=&lang=zh_CN
 *
 * @author zhengwei
 */
public class GetAccessTokenResponse extends JSONResponse {


    /**
     *
     */
    private static final long serialVersionUID = -1485327887795409953L;

    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    private String            openid;

    /**
     * 授权时用的accessToken
     */
    @JSONField(name = "access_token")
    private String            accessToken;

    /**
     * 用户刷新access_token
     */
    @JSONField(name = "refresh_token")
    private String            refreshToken;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String            scope;

    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    @JSONField(name = "expires_in")
    private Integer expiresIn;

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefresh_token() {
        return accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOpenId() {
        return this.openid;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
