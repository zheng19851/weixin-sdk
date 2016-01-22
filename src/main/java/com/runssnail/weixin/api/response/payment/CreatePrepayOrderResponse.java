package com.runssnail.weixin.api.response.payment;

import com.runssnail.weixin.api.domain.payment.TradeType;

/**
 * 创建预支付单相应对象
 *
 * @author zhengwei
 */
public class CreatePrepayOrderResponse extends PaymentResponse {

    /**
     *
     */
    private static final long serialVersionUID = 2607756506581018933L;

    /**
     * 预支付单id
     */
    private String            prepay_id;

    /**
     * 交易类型
     *
     * @see TradeType
     */
    private String            trade_type;

    /**
     * trade_type为NATIVE是有返回，此参数可直接生成二维码展示出来进行扫码支付
     */
    private String            code_url;

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getPrepayId() {
        return this.prepay_id;
    }

}
