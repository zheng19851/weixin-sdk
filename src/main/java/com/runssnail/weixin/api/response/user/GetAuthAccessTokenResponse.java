package com.runssnail.weixin.api.response.user;

import com.runssnail.weixin.api.response.JSONResponse;

/**
 * 网页授权获取access token
 *
 * @author zhengwei
 */
public class GetAuthAccessTokenResponse extends JSONResponse {

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
    private String            access_token;

    /**
     * 用户刷新access_token
     */
    private String            refresh_token;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String            scope;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     */
    private String            unionid;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenId() {
        return this.openid;
    }

    public String getAccessToken() {
        return this.access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

}
