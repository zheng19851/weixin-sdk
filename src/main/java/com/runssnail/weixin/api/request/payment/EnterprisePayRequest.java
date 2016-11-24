package com.runssnail.weixin.api.request.payment;

import com.runssnail.weixin.api.internal.annotations.AppIdWired;
import com.runssnail.weixin.api.internal.support.AppIdKeyAware;
import com.runssnail.weixin.api.internal.support.MerchantIdAware;
import com.runssnail.weixin.api.common.utils.SignUtils;
import com.runssnail.weixin.api.response.payment.EnterprisePayResponse;
import com.runssnail.weixin.api.internal.annotations.MerchantIdWired;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 企业付款，参数都是必填
 *
 * <xml>
 <mch_appid>wxe062425f740c30d8</mch_appid>
 <mchid>10000098</mchid>
 <nonce_str>3PG2J4ILTKCH16CQ2502SI8ZNMTM67VS</nonce_str>
 <partner_trade_no>100000982014120919616</partner_trade_no>
 <openid>ohO4Gt7wVPxIT1A9GjFaMYMiZY1s</openid>
 <check_name>OPTION_CHECK</check_name>
 <re_user_name>张三</re_user_name>
 <amount>100</amount>
 <desc>节日快乐!</desc>
 <spbill_create_ip>10.2.3.10</spbill_create_ip>
 <sign>C97BDBACF37622775366F38B629F45E3</sign>
 </xml>
 *
 * <p>
 * Created by zhengwei on 2016/3/23.
 */
@MerchantIdWired
@AppIdWired("mch_appid")
public class EnterprisePayRequest extends PaymentRequest<EnterprisePayResponse> implements AppIdKeyAware, MerchantIdAware {

    /**
     * 设备号 device_info
     */
    private String deviceInfo;

    /**
     * 商户订单号 partner_trade_no
     */
    private String orderNo;

    /**
     * 用户 openid
     */
    private String openId;

    /**
     * 收款用户姓名 re_user_name
     */
    private String userName;

    /**
     * 金额	amount 单位分
     */
    private Long amount;

    /**
     * 企业付款描述信息	desc
     */
    private String desc;

    /**
     * Ip地址 spbill_create_ip
     */
    private String ip;

    public EnterprisePayRequest() {

    }

    @Override
    public String getAppIdKey() {
        return "mch_appid";
    }

    @Override
    public String getMerchantIdKey() {
        return "mchid";
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String getApiUrl() {
        return "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    }

    @Override
    public Map<String, Object> getParams() {

        SortedMap<String, Object> params = new TreeMap<>();

        if (StringUtils.isNotBlank(this.deviceInfo)) {
            params.put("device_info", this.deviceInfo); // 设备号
        }

//        NO_CHECK：不校验真实姓名
//        FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账）
//        OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
        if(StringUtils.isBlank(this.userName)) {
            params.put("check_name", "NO_CHECK"); // 校验用户姓名选项
        } else {
            params.put("check_name", "OPTION_CHECK"); // 校验用户姓名选项
            params.put("re_user_name", this.userName); // 收款用户姓名
        }

        params.put("nonce_str", SignUtils.buildNonce()); // 随机数
        params.put("openid", this.openId); // 商户appid下，某用户的openid
        params.put("spbill_create_ip", this.ip); // ip
        params.put("partner_trade_no", this.orderNo); // 商户系统内部的订单号

        params.put("amount", this.amount); // 金额，单位分
        params.put("desc", this.desc); // 描述

        return params;
    }

    @Override
    public Class<EnterprisePayResponse> getResponseClass() {
        return EnterprisePayResponse.class;
    }
}
