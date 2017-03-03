package com.runssnail.weixin.api.response.pay;

import com.runssnail.weixin.api.exception.PayApiException;

/**
 * 企业付款响应对象
 *
 * Created by zhengwei on 2016/3/23.
 */
public class EnterprisePayResponse extends PayResponse {

    /**
     * 商户订单号
     */
    private String partner_trade_no;

    /**
     * 微信订单号
     */
    private String	payment_no;

    /**
     * 微信支付成功时间
     * 2015-05-19 15：26：59
     */
    private String	payment_time;

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    @Override
    public void check(String paySignKey) throws PayApiException {
        // ignore
    }
}
