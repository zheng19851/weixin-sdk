package com.runssnail.weixin.api.domain.user;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * 微信用户信息
 *
 * @author zhengwei
 */
public class User extends BaseDomain {

    /**
     *
     */
    private static final long serialVersionUID = 422465530058943744L;

    // openid 用户的唯一标识
    private String            openid;

    // nickname 用户昵称
    private String            nickName;

    // sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String            sex;

    // province 用户个人资料填写的省份
    private String            province;

    // city 普通用户个人资料填写的城市
    private String            city;

    // country 国家，如中国为CN
    private String            country;

    // headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private String            headimgurl;

    // privilege 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    private String            privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
