package com.runssnail.weixin.api.response.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.runssnail.weixin.api.response.JSONResponse;
import org.apache.commons.lang.StringUtils;

/**
 * 用户基本信息
 *
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN
 * <p>
 * Created by zhengwei on 16/5/30.
 */
public class GetUserInfoResponse extends JSONResponse {

    private static final long serialVersionUID = 5314387152892489442L;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private String subscribe;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JSONField(name = "subscribe_time")
    private String subscribeTime;

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
    @JSONField(name = "headimgurl")
    private String headImgUrl;

    // privilege 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    private String privilege;

    @JSONField(name = "unionid")
    private String unionId;//	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
    private String remark;//	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注

    @JSONField(name = "groupid")
    private String groupId;//	用户所在的分组ID（兼容旧的用户分组接口）

    @JSONField(name = "tagid_list")
    private String tagidlist;//	用户被打上的标签ID列表

    /**
     * 是否已订阅
     *
     * @return
     */
    public boolean isSubscribed() {
        return "1".equals(this.subscribe);
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTagidlist() {
        return tagidlist;
    }

    public void setTagidlist(String tagidlist) {
        this.tagidlist = tagidlist;
    }
}
