package com.runssnail.weixin.api.response.sns;

import com.alibaba.fastjson.annotation.JSONField;
import com.runssnail.weixin.api.response.JSONResponse;

/**
 * 网页开发-获取用户信息请求
 * <p>
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842&token=&lang=zh_CN
 *
 * @author zhengwei
 * @date 2016/10/28.
 */
public class GetUserInfoResponse extends JSONResponse {


    /**
     *
     */
    private static final long serialVersionUID = 422465530058943744L;

    // openid 用户的唯一标识
    private String openid;

    // nickname 用户昵称
    private String nickname;

    // sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String sex;

    // province 用户个人资料填写的省份
    private String province;

    // city 普通用户个人资料填写的城市
    private String city;

    // country 国家，如中国为CN
    private String country;

    // headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private String headimgurl;

    // privilege 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    private String privilege;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     */
    @JSONField(name = "unionid")
    private String unionId;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return this.nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
